package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.ServiceException;

public interface OrderService {
    /**
     * 
     * @param content 
     */
    void addMedicineToOrder(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    void showOrder(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    void changeQuantity(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param content 
     */
    boolean removeMedicineFromOrder(SessionRequestContent content) throws ServiceException;
    /**
     * 
     * @param OrderHasMedicine 
     */
    void updateRecipeAtOrderHasMedicine(OrderHasMedicine OrderHasMedicine) throws ServiceException;
    /**
     * 
     * @param orderId 
     * @param medicineId 
     */
    OrderHasMedicine findOrderHasMedicine(Integer orderId, Integer medicineId) throws ServiceException;
    /**
     * 
     * @param clientId 
     */
    int findCurrentOrderIdByUserId(Integer clientId) throws ServiceException;
    /**
     * 
     * @param orderId 
     * @param medicineId 
     * @param medicineQuantity 
     */
    void changeQuantityFromRecipe(Integer orderId, Integer medicineId, Integer medicineQuantity) throws ServiceException;
}

