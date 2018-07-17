package by.epam.pharmacy.service;

import by.epam.pharmacy.exception.CommandException;
import by.epam.pharmacy.exception.LogicException;
import by.epam.pharmacy.logic.UserRegistrar;
import by.epam.pharmacy.util.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterUserHandler implements RequestHandler<SessionRequestContent> {
    private static Logger logger = LogManager.getLogger();
    private UserRegistrar registrar = new UserRegistrar();


    @Override
    public String execute(SessionRequestContent sessionRequestContent) throws CommandException {
        try {
            return registrar.registerUser(sessionRequestContent);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
    }
        public void setRegistrar(UserRegistrar registrar) {
        this.registrar = registrar;
    }


}
