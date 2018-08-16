package by.epam.pharmacy.command;

import by.epam.pharmacy.exception.CommandException;

public interface RequestCommand<T> {
    /**
     * @param t can be HttpServletRequest, or it's content depend on action that came from cliebt side
     * @see by.epam.pharmacy.controller.CommandMapper
     * @see SessionRequestContent
     * @see by.epam.pharmacy.controller.PageRouter
     * @return String type with page path to redirect to
     * @throws CommandException
     */
    String execute(T t) throws CommandException;
}

