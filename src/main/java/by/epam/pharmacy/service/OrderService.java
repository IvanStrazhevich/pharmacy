package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.ServiceException;

public interface OrderService {
    void addMedicineToOrder(SessionRequestContent sessionRequestContent) throws ServiceException;
    void showOrder(SessionRequestContent sessionRequestContent) throws ServiceException;
}
