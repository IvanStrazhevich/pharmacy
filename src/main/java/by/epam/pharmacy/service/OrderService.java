package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.ServiceException;

public interface OrderService {
    /**
     * Add medicine to order
     *
     * @param content
     */
    void addMedicineToOrder(SessionRequestContent content) throws ServiceException;

    /**
     * Retrieve Order info
     *
     * @param content
     */
    void showOrder(SessionRequestContent content) throws ServiceException;

    /**
     * Change quantity of medicine
     *
     * @param content
     */
    void changeQuantity(SessionRequestContent content) throws ServiceException;

    /**
     * Remove medicine from order
     *
     * @param content
     * @return true if succeed
     */
    boolean removeMedicineFromOrder(SessionRequestContent content) throws ServiceException;

    /**
     * Update Recipe id at OrderHasMedicine
     *
     * @param orderHasMedicine
     */
    void updateRecipeAtOrderHasMedicine(OrderHasMedicine orderHasMedicine) throws ServiceException;

    /**
     * Find OrderHasMedicine by params
     *
     * @param orderId
     * @param medicineId
     * @return OrderHasMedicine
     */
    OrderHasMedicine findOrderHasMedicine(Integer orderId, Integer medicineId) throws ServiceException;

    /**
     * Find Id of Order by param
     *
     * @param clientId
     * @return id of order
     */
    int findCurrentOrderIdByUserId(int clientId) throws ServiceException;

    /**
     * Change quantity of medicine on params
     *
     * @param orderId          id of order
     * @param medicineId       id of medicine
     * @param medicineQuantity id of quantity
     */
    void changeQuantityFromRecipe(Integer orderId, Integer medicineId, Integer medicineQuantity) throws ServiceException;
}

