package by.epam.pharmacy.service;

import javax.servlet.ServletException;
import java.io.IOException;

public interface RequestHandler<T> {
    String execute(T sessionRequestContent) throws ServletException, IOException;
}
