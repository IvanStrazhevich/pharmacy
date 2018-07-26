package by.epam.pharmacy.tag;

import by.epam.pharmacy.util.ResourceManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class GreetingTag extends TagSupport {
    private String accessLevel;
    private String login;
    private static final String MESSAGE = "message.welcomePage";
    private static final String MESSAGE_TO_USER = "message.userGreeting";

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String greeting = ResourceManager.INSTANCE.getString(MESSAGE) + login + "<br>"
                    + ResourceManager.INSTANCE.getString(MESSAGE_TO_USER) + " " + accessLevel;
            pageContext.getOut().write(greeting);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
