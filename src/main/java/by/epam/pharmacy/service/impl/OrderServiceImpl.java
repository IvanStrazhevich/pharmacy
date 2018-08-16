package by.epam.pharmacy.service.impl;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.dao.impl.MedicineDaoImpl;
import by.epam.pharmacy.dao.impl.OrderDaoImpl;
import by.epam.pharmacy.dao.impl.OrderHasMedicineDaoImpl;
import by.epam.pharmacy.dao.impl.UserDaoImpl;
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

/**
 *
 */
public class OrderServiceImpl implements OrderService {
    private static final String MESSAGE_ADDED = "message.medicineAddedToOrder";
    private static final String MESSAGE_ADD = "message.chooseQuantity";
    private static final String MESSAGE_NEED_LOGIN = "message.needLogin";
    private static Logger logger = LogManager.getLogger();
    private Encodable encodable = new ShaConverter();

    /**
     * Add medicine to order
     *
     * @param content
     */
    @Override
    public void addMedicineToOrder(SessionRequestContent content) throws ServiceException {
        if (content.getSessionAttributes().get(AttributeName.LOGGED.getAttribute()) != null
                && content.getSessionAttributes().get(AttributeName.LOGGED.getAttribute()).equals(AttributeName.LOGGED.getAttribute())) {
            if (content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()) != "") {
                int medicineId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()));
                int medicineQuantity = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()));
                String clientLogin = content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString();
                int userId = findUserId(clientLogin);
                logger.debug(userId);
                int orderId = createOrder(userId, medicineId);
                logger.debug("adding medicine to order" + orderId);
                createOrUpdateMedicineInOrder(medicineId, orderId, medicineQuantity);
                content.getRequestAttributes().put(AttributeName.MEDICINE_ADDED.getAttribute(),
                        ResourceManager.INSTANCE.getString(MESSAGE_ADDED));
            } else {
                content.getRequestAttributes().put(AttributeName.MEDICINE_ADDED.getAttribute(),
                        ResourceManager.INSTANCE.getString(MESSAGE_ADD));

            }
        } else {
            content.getRequestAttributes().put(AttributeName.NEED_LOGIN.getAttribute(), ResourceManager.INSTANCE.getString(MESSAGE_NEED_LOGIN));
        }
    }

    /**
     * Update Recipe id at OrderHasMedicine
     *
     * @param orderHasMedicine
     */
    @Override
    public void updateRecipeAtOrderHasMedicine(OrderHasMedicine orderHasMedicine) throws ServiceException {
        try (OrderHasMedicineDaoImpl orderHasMedicineDao = new OrderHasMedicineDaoImpl()) {
            orderHasMedicineDao.updateRecipe(orderHasMedicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find OrderHasMedicine by params
     *
     * @param orderId
     * @param medicineId
     * @return OrderHasMedicine
     */
    public OrderHasMedicine findOrderHasMedicine(Integer orderId, Integer medicineId) throws ServiceException {
        OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
        try (OrderHasMedicineDaoImpl orderHasMedicineDao = new OrderHasMedicineDaoImpl()) {
            orderHasMedicine = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderHasMedicine;
    }

    /**
     * Remove medicine from order
     *
     * @param content
     * @return true if succeed
     */
    @Override
    public boolean removeMedicineFromOrder(SessionRequestContent content) throws ServiceException {
        boolean success = false;
        int medicineId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()));
        int orderId = Integer.valueOf(content.getRequestParameters().get(AttributeName.ORDER_ID.getAttribute()));
        try (OrderHasMedicineDaoImpl orderHasMedicineDao = new OrderHasMedicineDaoImpl()) {
            OrderHasMedicine orderHasMedicineDB = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId);
            if (orderHasMedicineDao.deleteMedicineFromOrder(orderId, medicineId)) {
                logger.debug("deleted");
                recountStorageQuantity(medicineId, 0, orderHasMedicineDB.getMedicineQuantity());
                success = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return success;
    }


    /**
     * Retrieve Order info
     *
     * @param content
     */
    @Override
    public void showOrder(SessionRequestContent content) throws ServiceException {
        String clientLogin = content.getSessionAttributes().get(AttributeName.LOGIN.getAttribute()).toString();
        int userId = findUserId(clientLogin);
        try (OrderDaoImpl orderDao = new OrderDaoImpl()) {
            int orderId = orderDao.findCurrentOrderByUserId(userId).getOrderId();
            Order order = orderDao.showOrderWithMedicineByOrderId(orderId);
            ArrayList<OrderHasMedicine> medicines = order.getOrderHasMedicines();
            BigDecimal orderSum = new BigDecimal(0);
            for (OrderHasMedicine medicine : medicines) {
                logger.debug(medicine.getMedicineSum());
                BigDecimal medicineSum = medicine.getMedicineSum();
                orderSum = orderSum.add(medicineSum);
            }
            logger.debug(orderSum);
            order.setOrderSum(orderSum);
            logger.debug(order);
            orderDao.update(order);
            content.getRequestAttributes().put(AttributeName.ORDER.getAttribute(), order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Change quantity of medicine
     *
     * @param content
     */
    @Override
    public void changeQuantity(SessionRequestContent content) throws ServiceException {
        int medicineId = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_ID.getAttribute()));
        int orderId = Integer.valueOf(content.getRequestParameters().get(AttributeName.ORDER_ID.getAttribute()));
        int medicineQuantity = Integer.valueOf(content.getRequestParameters().get(AttributeName.MEDICINE_QUANTITY.getAttribute()));
        updateQuantityByOrderMedicineQuantity(orderId, medicineId, medicineQuantity);

    }

    /**
     * Find Id of Order by param
     *
     * @param clientId
     * @return id of order
     */
    public int findCurrentOrderIdByUserId(int clientId) throws ServiceException {
        try (OrderDaoImpl orderDao = new OrderDaoImpl()) {
            return orderDao.findCurrentOrderByUserId(clientId).getOrderId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Change quantity of medicine on params
     *
     * @param orderId          id of order
     * @param medicineId       id of medicine
     * @param medicineQuantity id of quantity
     */
    public void changeQuantityFromRecipe(Integer orderId, Integer medicineId, Integer medicineQuantity) throws ServiceException {
        updateQuantityByOrderMedicineQuantity(orderId, medicineId, medicineQuantity);

    }

    private void updateQuantityByOrderMedicineQuantity(Integer orderId, Integer medicineId, Integer medicineQuantity) throws ServiceException {
        try (OrderHasMedicineDaoImpl orderHasMedicineDao = new OrderHasMedicineDaoImpl()) {
            OrderHasMedicine orderHasMedicine = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId);
            orderHasMedicine.setMedicineQuantity(medicineQuantity);
            updateQuantityByOrderMedicineQuantity(orderHasMedicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean updateQuantityByOrderMedicineQuantity(OrderHasMedicine orderHasMedicine) throws ServiceException {
        boolean success = false;
        try (OrderHasMedicineDaoImpl orderHasMedicineDao = new OrderHasMedicineDaoImpl()) {
            OrderHasMedicine orderHasMedicineDB = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderHasMedicine.getOrderId(), orderHasMedicine.getMedicineId());
            orderHasMedicine.setMedicineSum(countMedicineSum(orderHasMedicine.getMedicineQuantity(), orderHasMedicine.getMedicineId()));
            logger.debug(orderHasMedicine);
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
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            Medicine medicine = new Medicine();
            medicine = medicineDao.findEntityById(medicineId);
            medicine.setQuantityAtStorage(medicine.getQuantityAtStorage() + medicineQuantityDB - medicineQuantity);
            medicineDao.update(medicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private BigDecimal countMedicineSum(int quantity, int medicineId) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            logger.debug(medicineId);
            BigDecimal price = medicineDao.findEntityById(medicineId).getPrice();
            logger.debug(price);
            return price.multiply(new BigDecimal(quantity));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    private void updateStorageQuantity(int medicineId, int medicineQuantity) throws ServiceException {
        try (MedicineDaoImpl medicineDao = new MedicineDaoImpl()) {
            Medicine medicine = new Medicine();
            medicine = medicineDao.findEntityById(medicineId);
            medicine.setQuantityAtStorage(medicine.getQuantityAtStorage() - medicineQuantity);
            medicineDao.update(medicine);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private int createOrder(int userId, int medicineId) throws ServiceException {
        Order order = new Order();
        order.setClientId(userId);
        order.setOrderSum(BigDecimal.valueOf(0));
        logger.debug("Creating order");
        logger.debug(order);
        int orderId = createOrUpdateOrder(order);
        logger.debug(orderId);
        return orderId;
    }

    private int createOrUpdateOrder(Order order) throws ServiceException {
        try (OrderDaoImpl orderDao = new OrderDaoImpl()) {
            if (orderDao.findCurrentOrderByUserId(order.getClientId()).getOrderId() != 0
                    && !orderDao.findCurrentOrderByUserId(order.getClientId()).isPayed()) {
                logger.debug("order updated");
                orderDao.update(order);
                order.setOrderId(orderDao.findCurrentOrderByUserId(order.getClientId()).getOrderId());
            } else {
                if (orderDao.create(order)) {
                    logger.debug(" order created");
                    order.setOrderId(orderDao.findLastInsertId());
                    logger.debug(order.getOrderId());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return order.getOrderId();
    }

    private boolean createOrUpdateMedicineInOrder(int medicineId, int orderId, int medicineQuantity) throws ServiceException {
        boolean success = false;
        try (OrderHasMedicineDaoImpl orderHasMedicineDao = new OrderHasMedicineDaoImpl()) {
            OrderHasMedicine orderHasMedicine = new OrderHasMedicine();
            orderHasMedicine.setMedicineId(medicineId);
            orderHasMedicine.setOrderId(orderId);
            orderHasMedicine.setMedicineQuantity(medicineQuantity);
            orderHasMedicine.setMedicineSum(countMedicineSum(medicineQuantity, medicineId));
            logger.debug(orderHasMedicine);
            if (orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId).getMedicineId() == medicineId
                    && orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId).getOrderId() == orderId) {
                OrderHasMedicine orderHasMedicineFromDB
                        = orderHasMedicineDao.findOrderHasMedicineByOrderIdMedicineId(orderId, medicineId);
                orderHasMedicine.setMedicineQuantity(orderHasMedicine.getMedicineQuantity()
                        + orderHasMedicineFromDB.getMedicineQuantity());
                orderHasMedicine.setMedicineSum(orderHasMedicine.getMedicineSum()
                        .add(orderHasMedicineFromDB.getMedicineSum()));
                if (orderHasMedicineDao.update(orderHasMedicine)) {
                    updateStorageQuantity(medicineId, medicineQuantity);
                    success = true;
                }
            } else {
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

    private int findUserId(String clientLogin) throws ServiceException {
        logger.debug(clientLogin);
        clientLogin = encodable.encode(clientLogin);
        try (UserDaoImpl userDao = new UserDaoImpl()) {
            return userDao.findUserByLogin(clientLogin).getUserId();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * Set encoding imlementation
     *
     * @param encodable
     */
    public void setEncodable(Encodable encodable) {
        this.encodable = encodable;
    }
}

