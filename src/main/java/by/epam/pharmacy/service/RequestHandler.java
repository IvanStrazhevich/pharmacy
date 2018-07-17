package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.CommandException;

public interface RequestHandler<T> {
    String execute(T sessionRequestContent) throws CommandException;
}
