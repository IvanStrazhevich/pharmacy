package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.ServiceException;

public interface OrderService {
    void addMedicineToOrder(SessionRequestContent sessionRequestContent) throws ServiceException;
    void showOrder(SessionRequestContent sessionRequestContent) throws ServiceException;
    void changeQuantity(SessionRequestContent sessionRequestContent) throws ServiceException;
    boolean removeMedicineFromOrder(SessionRequestContent sessionRequestContent) throws ServiceException;
    void updateRecipeAtOrderHasMedicine(OrderHasMedicine orderHasMedicine) throws ServiceException;
    OrderHasMedicine findOrderHasMedicine(Integer orderId, Integer medicineId) throws ServiceException;
    int findCurrentOrderIdByUserId(Integer clientId) throws ServiceException;
    void changeQuantityFromRecipe(Integer orderId, Integer medicineId, Integer medicineQuantity) throws ServiceException;
}
