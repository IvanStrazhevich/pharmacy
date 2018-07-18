package by.epam.pharmacy.service;

import by.epam.pharmacy.controller.SessionRequestContent;
import by.epam.pharmacy.exception.LogicException;

public interface ClientService {
    void createClientDetail(SessionRequestContent sessionRequestContent) throws LogicException;
    void findClientDetail(SessionRequestContent sessionRequestContent) throws LogicException;
}
