package by.epam.pharmacy.tag;

import by.epam.pharmacy.service.ResourceManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class HelloTag extends TagSupport {
    private String role;
    private String login;
    private static final String MESSAGE = "message.welcomePage";
    private static final String MESSAGE_TO_USER = "message.userGreeting";

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String greeting = ResourceManager.INSTANCE.getString(MESSAGE) + login + " "
                    + ResourceManager.INSTANCE.getString(MESSAGE_TO_USER) + role;
            pageContext.getOut().write("<hr/>" + greeting + "<hr/>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
