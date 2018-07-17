package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.LogicException;
import by.epam.pharmacy.logic.UserAuthorisation;
import by.epam.pharmacy.command.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckUserHandler implements RequestHandler<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private UserAuthorisation userAuthorisation = new UserAuthorisation();


    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            return userAuthorisation.logIn(sessionRequestContent);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
    }
}

