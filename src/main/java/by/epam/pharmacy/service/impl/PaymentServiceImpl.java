package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.*;
import by.epam.pharmacy.entity.ClientAmount;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.Payment;
import by.epam.pharmacy.entity.PharmacyAccount;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.Encodable;
import by.epam.pharmacy.service.PaymentService;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService {
    private static final String MESSAGE_PAYED = "message.payed";
    private static final String MESSAGE_NOT_PAYED = "message.notPayed";
    private static final int INITIAL_AMOUNT = 0;
    private Encodable encodable = new ShaConverter();
    private static Logger logger = LogManager.getLogger();

    /**
     * @param content
     * @throws ServiceException
     */
    @Override
    public void createOrUpdatePayment(SessionRequestContent content) throws ServiceException {
        BigDecimal orderSum = new BigDecimal(content.getRequestParameters().get(AttributeName.ORDER_SUM.getAttribute()));
        int orderId = Integer.valueOf(content.getRequestParameters().get(AttributeName.ORDER_ID.getAttribute()));
        Payment payment = new Payment();
        try (PaymentDaoImpl paymentDao = new PaymentDaoImpl()) {
            payment.setOrderId(orderId);
            payment.setOrderSum(orderSum);
            logger.info(paymentDao.findPaymentByOrderId(orderId).getOrderId());
            logger.info(paymentDao.findPaymentByOrderId(orderId).isPaymentConfirmed());
            if (paymentDao.findPaymentByOrderId(orderId).getOrderId() == orderId
                    && !paymentDao.findPaymentByOrderId(orderId).isPaymentConfirmed()) {
                int paymentId = paymentDao.findPaymentByOrderId(orderId).getPaymentId();
                payment.setPaymentId(paymentId);
                paymentDao.update(payment);
            } else {
                paymentDao.create(payment);
                int paymentId = paymentDao.findLastInsertId();
                payment.setPaymentId(paymentId);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        content.getRequestAttributes().put(AttributeName.PAYMENT.getAttribute(), payment);
    }

    @Override
    public void proceedToPayment(SessionRequestContent content) throws ServiceException {
        BigDecimal orderSum = new BigDecimal(content.getRequestParameters().get(AttributeName.ORDER_SUM.getAttribute()));
        String login = content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString();
        logger.info(login);
        String shaLogin = encodable.encode(login);
        int userId = 0;
        try (UserDaoImpl userDao = new UserDaoImpl();
             AmountDaoImpl amountDao = new AmountDaoImpl();
             PharmacyAccountDaoImpl accountDao = new PharmacyAccountDaoImpl()) {
            userId = userDao.findUserByLogin(shaLogin).getUserId();
            logger.info(userId);
            if (accountDao.findEntityById(userId).getClientId() == 0) {
                PharmacyAccount account = new PharmacyAccount();
                account.setAccountDebit(new BigDecimal(INITIAL_AMOUNT));
                account.setAccountCredit(new BigDecimal(INITIAL_AMOUNT));
                account.setClientId(userId);
                accountDao.create(account);
            }
            if (amountDao.findEntityById(userId).getClientId() == 0) {
                ClientAmount amount = new ClientAmount();
                amount.setAmountDebit(new BigDecimal(INITIAL_AMOUNT));
                amount.setAmountCredit(new BigDecimal(INITIAL_AMOUNT));
                amount.setClientId(userId);
                amountDao.create(amount);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.info(orderSum+" "+userId);
        PharmacyAccount tempPharmacyAccount = checkAmount(orderSum, userId);
        content.getRequestAttributes().put(AttributeName.ACCOUNT.getAttribute(), tempPharmacyAccount);

    }

    @Override
    public void makePayment(SessionRequestContent content) throws ServiceException {
        boolean made = false;
        int paymentId = Integer.valueOf(content.getRequestParameters().get(AttributeName.PAYMENT_ID.getAttribute()));
        BigDecimal accountDebit = new BigDecimal(content.getRequestParameters().get(AttributeName.ACCOUNT_DEBIT.getAttribute()));
        BigDecimal accountCredit = new BigDecimal(content.getRequestParameters().get(AttributeName.ACCOUNT_CREDIT.getAttribute()));
        String login = content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString();
        String shaLogin = encodable.encode(login);
        int userId = 0;
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            userId = userDao.findUserByLogin(shaLogin).getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        made = confirmAndProcessPayment(accountDebit, accountCredit, userId);
        if (made) {
            try (PaymentDaoImpl paymentDao = new PaymentDaoImpl();
                 OrderDaoImpl orderDao = new OrderDaoImpl()) {
                Payment payment = paymentDao.findEntityById(paymentId);
                payment.setPaymentConfirmed(made);
                Order order = orderDao.findCurrentOrderByUserId(userId);
                order.setPayed(made);
                paymentDao.update(payment);
                orderDao.update(order);
                content.getRequestAttributes().put(AttributeName.PAYED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_PAYED));
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            content.getRequestAttributes().put(AttributeName.PAYED.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_NOT_PAYED));
        }
    }

    private PharmacyAccount checkAmount(BigDecimal orderSum, int userId) throws ServiceException {
        PharmacyAccount pharmacyAccountTemp = new PharmacyAccount();
        try (AmountDaoImpl amountDao = new AmountDaoImpl();
             PharmacyAccountDaoImpl pharmacyAccountDao = new PharmacyAccountDaoImpl()) {
            ClientAmount amountTemp = amountDao.findEntityById(userId);
            logger.info(amountTemp);
            pharmacyAccountTemp = pharmacyAccountDao.findEntityById(userId);
            BigDecimal payAbility = amountTemp.getAmountDebit().subtract(orderSum);
            logger.info(payAbility);
            if (payAbility.compareTo(new BigDecimal(0)) >= 0) {
                pharmacyAccountTemp.setAccountDebit(orderSum);
                pharmacyAccountTemp.setAccountCredit(new BigDecimal(INITIAL_AMOUNT));
            } else {
                pharmacyAccountTemp.setAccountDebit(amountTemp.getAmountDebit());
                pharmacyAccountTemp.setAccountCredit(payAbility.abs());
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pharmacyAccountTemp;
    }

    private boolean confirmAndProcessPayment(BigDecimal accountDebit, BigDecimal accountCredit, int userId) throws ServiceException {
        boolean confirmed = false;
        try (AmountDaoImpl amountDao = new AmountDaoImpl();
             PharmacyAccountDaoImpl pharmacyAccountDao = new PharmacyAccountDaoImpl()) {
            ClientAmount amount = amountDao.findEntityById(userId);
            PharmacyAccount pharmacyAccount = pharmacyAccountDao.findEntityById(userId);
            amount.setAmountDebit(amount.getAmountDebit().subtract(accountDebit));
            amount.setAmountCredit(amount.getAmountCredit().add(accountCredit));
            pharmacyAccount.setAccountDebit(pharmacyAccount.getAccountDebit().add(accountDebit));
            pharmacyAccount.setAccountCredit(pharmacyAccount.getAccountCredit().add(accountCredit));
            amountDao.update(amount);
            pharmacyAccountDao.update(pharmacyAccount);
            confirmed = true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return confirmed;
    }
}

