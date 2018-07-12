package by.epam.pharmacy.tag;

import by.epam.pharmacy.util.LanguageSwitchable;
import by.epam.pharmacy.util.LanguageSwitcher;
import by.epam.pharmacy.service.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

@SuppressWarnings("serial")
public class InfoTimeTag extends TagSupport {
    private static final String MESSAGE_TIME = "message.time";
    private static final String MESSAGE_LOCALE = "message.locale";
    private static Logger logger = LogManager.getLogger();
    private LanguageSwitchable languageSwitcher = new LanguageSwitcher();
    private String locale;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        String region = null;
        if (null != locale) {
            region = ResourceManager.INSTANCE.getString(MESSAGE_LOCALE) + " <b> " + locale + " </b><hr/> ";
        } else {
            languageSwitcher.langSwitch(pageContext.getRequest());
            locale = Locale.getDefault().stripExtensions().toString();
            region = ResourceManager.INSTANCE.getString(MESSAGE_LOCALE) + " <b> " + locale + " </b><hr/> ";

        }
        String time = "<hr/>" + ResourceManager.INSTANCE.getString(MESSAGE_TIME) + " <b> "
                + Calendar.getInstance(Locale.getDefault()).getTime() + " </b><hr/>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(time + region);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    public void setLanguageSwitcher(LanguageSwitchable languageSwitcher) {
        this.languageSwitcher = languageSwitcher;
    }
}