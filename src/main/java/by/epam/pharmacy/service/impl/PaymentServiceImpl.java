package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.PaymentDaoImpl;
import by.epam.pharmacy.entity.Payment;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class PaymentServiceImpl implements PaymentService {
    Logger logger = LogManager.getLogger();
    @Override
    public void createPayment(SessionRequestContent content) throws ServiceException {
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
    public void makePayment(SessionRequestContent content) {

    }
}
