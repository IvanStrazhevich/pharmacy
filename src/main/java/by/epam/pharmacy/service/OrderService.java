package by.epam.pharmacy.service;

import by.epam.pharmacy.command.SessionRequestContent;
import by.epam.pharmacy.entity.OrderHasMedicine;
import by.epam.pharmacy.exception.ServiceException;

public interface OrderService {
    void addMedicineToOrder(SessionRequestContent content) throws ServiceException;
    void showOrder(SessionRequestContent content) throws ServiceException;
    void changeQuantity(SessionRequestContent content) throws ServiceException;
    boolean removeMedicineFromOrder(SessionRequestContent content) throws ServiceException;
    void updateRecipeAtOrderHasMedicine(OrderHasMedicine OrderHasMedicine) throws ServiceException;
    OrderHasMedicine findOrderHasMedicine(Integer orderId, Integer medicineId) throws ServiceException;
    int findCurrentOrderIdByUserId(Integer clientId) throws ServiceException;
    void changeQuantityFromRecipe(Integer orderId, Integer medicineId, Integer medicineQuantity) throws ServiceException;
}
