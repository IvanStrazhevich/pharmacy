package by.epam.pharmacy.command;

import by.epam.pharmacy.exception.CommandException;

public interface RequestCommand<T> {
    /**
     * 
     * @param content 
     */
    String execute(T content) throws CommandException;
}

