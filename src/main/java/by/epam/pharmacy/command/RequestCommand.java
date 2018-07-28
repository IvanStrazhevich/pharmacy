package by.epam.pharmacy.command;

import by.epam.pharmacy.exception.CommandException;

public interface RequestCommand<T> {
    String execute(T content) throws CommandException;
}
