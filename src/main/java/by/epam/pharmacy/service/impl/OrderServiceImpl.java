package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeEnum;
import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.dao.impl.MedicineDao;
import by.epam.pharmacy.dao.impl.OrderDao;
import by.epam.pharmacy.dao.impl.OrderHasMedicineDao;
import by.epam.pharmacy.dao.impl.UserDao;
import by.epam.pharmacy.entity.Medicine;
import by.epam.pharmacy.entity.Order;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.Encodable;
import by.epam.pharmacy.service.OrderService;
import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {

    private static final String MESSAGE_ADDED = "message.medicineAddedToOrder";
    private static Logger logger = LogManager.getLogger();
    private Encodable encodable = new SHAConverter();


    @Override
    public void addMedicineToOrder(SessionRequestContent sessionRequestContent) throws ServiceException {
        int medicineId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()));
        int medicineQuantity = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_QUANTITY.getAttribute()));
        String clientLogin = sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGIN.getAttribute()).toString();
        int userId = findUserId(clientLogin);
        logger.info(userId);
        int orderId = createOrder(userId, medicineId);
        logger.info("adding medicine to order" + orderId);
        createOrUpdateMedicineInOrder(medicineId, orderId, medicineQuantity);
        sessionRequestContent.getRequestAttributes().put(AttributeEnum.MEDICINE_ADDED.getAttribute(),
                ResourceManager.INSTANCE.getString(MESSAGE_ADDED));
    }

    @Override
    public void showOrder(SessionRequestContent sessionRequestContent) throws ServiceException {
        String clientLogin = sessionRequestContent.getSessionAttributes().get(AttributeEnum.LOGIN.getAttribute()).toString();
        int userId = findUserId(clientLogin);
        try (OrderDao orderDao = new OrderDao()) {
            int orderId = orderDao.findCurrentOrderByUserId(userId).getOrderId();
            Order order = orderDao.showOrderWithMedicineByOrderId(orderId);
            logger.info(order);
            sessionRequestContent.getRequestAttributes().put(AttributeEnum.ORDER.getAttribute(), order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeQuantity(SessionRequestContent sessionRequestContent) throws ServiceException {
        int medicineId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_ID.getAttribute()));
        int orderId = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.ORDER_ID.getAttribute()));
        int medicineQuantity = Integer.valueOf(sessionRequestContent.getRequestParameters().get(AttributeEnum.MEDICINE_QUANTITY.getAttribute()));
        try (OrderHasMedicineDao orderHasMedicineDao = new OrderHasMedicineDao()) {
            OrderHasMedicine orderHasMedicine = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId);
            orderHasMedicine.setMedicineQuantity(medicineQuantity);
            updateQuantity(orderHasMedicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    private boolean updateQuantity(OrderHasMedicine orderHasMedicine) throws ServiceException {
        boolean success = false;
        try (OrderHasMedicineDao orderHasMedicineDao = new OrderHasMedicineDao()) {
            OrderHasMedicine orderHasMedicineDB = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderHasMedicine.getOrderId(), orderHasMedicine.getMedicineId());
            orderHasMedicine.setMedicineSum(countMedicineSum(orderHasMedicine.getMedicineQuantity(), orderHasMedicine.getMedicineId()));
            logger.info(orderHasMedicine);
            if (orderHasMedicineDao.update(orderHasMedicine)) {
                recountStorageQuantity(orderHasMedicine.getMedicineId(), orderHasMedicine.getMedicineQuantity(), orderHasMedicineDB.getMedicineQuantity());
                success = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return success;
    }

    private void recountStorageQuantity(int medicineId, int medicineQuantity, int medicineQuantityDB) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            Medicine medicine = new Medicine();
            medicine = medicineDao.findEntityById(medicineId);
            medicine.setQuantityAtStorage(medicine.getQuantityAtStorage() + medicineQuantityDB - medicineQuantity);
            medicineDao.update(medicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private BigDecimal countMedicineSum(int quantity, int medicineId) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            BigDecimal price = medicineDao.findEntityById(medicineId).getPrice();
            return price.multiply(new BigDecimal(quantity));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    private void updateStorageQuantity(int medicineId, int medicineQuantity) throws ServiceException {
        try (MedicineDao medicineDao = new MedicineDao()) {
            Medicine medicine = new Medicine();
            medicine = medicineDao.findEntityById(medicineId);
            medicine.setQuantityAtStorage(medicine.getQuantityAtStorage() - medicineQuantity);
            medicineDao.update(medicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private Integer createOrder(int userId, int medicineId) throws ServiceException {
        Order order = new Order();
        order.setClientId(userId);
        order.setMedicineSum(BigDecimal.valueOf(0));
        ArrayList<Integer> medicineIds = order.getMedicineIdList();
        if (medicineIds == null) {
            medicineIds = new ArrayList<>();
        }
        medicineIds.add(medicineId);
        order.setMedicineIdList(medicineIds);
        logger.info("Creating order");
        logger.info(medicineId);
        logger.info(order);
        int orderId = createOrUpdateOrder(order);
        logger.info(orderId);
        return orderId;
    }

    private Integer createOrUpdateOrder(Order order) throws ServiceException {
        try (OrderDao orderDao = new OrderDao()) {
            if (orderDao.findCurrentOrderByUserId(order.getClientId()).getOrderId() != 0
                    && !orderDao.findCurrentOrderByUserId(order.getClientId()).isPayed()) {
                logger.info("order updated");
                orderDao.update(order);
                order.setOrderId(orderDao.findCurrentOrderByUserId(order.getClientId()).getOrderId());
            } else {
                if (orderDao.create(order)) {
                    logger.info(" order created");
                    order.setOrderId(orderDao.findLastInsertId());
                    logger.info(order.getOrderId());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return order.getOrderId();
    }

    private boolean createOrUpdateMedicineInOrder(int medicineId, int orderId, int medicineQuantity) throws ServiceException {
        boolean success = false;
        try (OrderHasMedicineDao orderHasMedicineDao = new OrderHasMedicineDao()) {
            OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
            orderHasMedicine.setMedicineId(medicineId);
            orderHasMedicine.setOrderId(orderId);
            orderHasMedicine.setMedicineQuantity(medicineQuantity);
            orderHasMedicine.setMedicineSum(countMedicineSum(medicineQuantity, medicineId));
            logger.info(orderHasMedicine);
            if (orderHasMedicineDao.findOrderHasMedicineByMedicineId(medicineId).getMedicineId() == medicineId
                    && orderHasMedicineDao.findOrderHasMedicineByMedicineId(medicineId).getOrderId() == orderId) {
                logger.info("updates cause id = " + medicineId + " " + orderHasMedicineDao.findOrderHasMedicineByMedicineId(medicineId).getMedicineId());
                OrderHasMedicine orderHasMedicineFromDB
                        = orderHasMedicineDao.findOrderHasMedicineByMedicineId(medicineId);
                orderHasMedicine.setMedicineQuantity(orderHasMedicine.getMedicineQuantity()
                        + orderHasMedicineFromDB.getMedicineQuantity());
                orderHasMedicine.setMedicineSum(orderHasMedicine.getMedicineSum()
                        .add(orderHasMedicineFromDB.getMedicineSum()));
                if (orderHasMedicineDao.update(orderHasMedicine)) {
                    updateStorageQuantity(medicineId, medicineQuantity);
                    success = true;
                }
            } else {
                logger.info("creates cause id is not equals " + medicineId + " " + orderHasMedicineDao.findOrderHasMedicineByMedicineId(medicineId).getMedicineId());
                if (orderHasMedicineDao.create(orderHasMedicine)) {
                    updateStorageQuantity(medicineId, medicineQuantity);
                    success = true;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return success;
    }

    private Integer findUserId(String clientLogin) throws ServiceException {
        logger.info(clientLogin);
        clientLogin = encodable.encode(clientLogin);
        try (UserDao userDao = new UserDao()) {
            return userDao.findUserByLogin(clientLogin).getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
