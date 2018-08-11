package by.epam.pharmacy.tag;

import by.epam.pharmacy.command.AttributeName;
import by.epam.pharmacy.dao.impl.ClientDetailDaoImpl;
import by.epam.pharmacy.entity.ClientDetail;
import by.epam.pharmacy.exception.DaoException;
import by.epam.pharmacy.exception.ServiceException;
import by.epam.pharmacy.service.ClientService;
import by.epam.pharmacy.service.impl.ClientServiceImpl;
import by.epam.pharmacy.util.ResourceManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 *
 */
@SuppressWarnings("serial")
public class GreetingTag extends TagSupport {
    private static final String MESSAGE = "message.welcomePage";
    private static final String MESSAGE_TO_USER = "message.userGreeting";
    private ClientService clientService = new ClientServiceImpl();
    private String accessLevel;
    private String login;
    private String photo;

    /**
     *
     */

    @Override
    public int doStartTag() throws JspException {
        String greeting = null;
        try (ClientDetailDaoImpl clientDetailDao = new ClientDetailDaoImpl()) {
            if (pageContext.getSession().getAttribute(AttributeName.LOGIN.getAttribute()) != null) {
                if (pageContext.getSession().getAttribute(AttributeName.PHOTO.getAttribute()) == null) {
                    int userId = clientService.findClientId(pageContext.getSession().getAttribute(AttributeName.LOGIN.getAttribute()).toString());
                    ClientDetail clientDetail = clientDetailDao.findEntityById(userId);
                    photo = clientDetail.getPhoto();
                    pageContext.getSession().setAttribute(AttributeName.PHOTO.getAttribute(), photo);
                }
                if (photo == null) {
                    greeting = ResourceManager.INSTANCE.getString(MESSAGE) + login + "<br>"
                            + ResourceManager.INSTANCE.getString(MESSAGE_TO_USER) + " " + accessLevel;
                } else {
                    greeting = ResourceManager.INSTANCE.getString(MESSAGE) + login + "<br>"
                            + ResourceManager.INSTANCE.getString(MESSAGE_TO_USER) + " " + accessLevel
                            + "<p><img src=\"" + photo + "\" alt=\"Sorry, we are already fixing this\" width=\"60\" height=\"60\" class=\"img-fluid rounded-circle\"></p>";
                }
            }
            pageContext.getOut().write(greeting);

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        } catch (DaoException e) {
            throw new JspException("Dao exception", e);
        } catch (ServiceException e) {
            throw new JspException("Service exception", e);
        }
        return SKIP_BODY;
    }

    /**
     * @param accessLevel
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}

