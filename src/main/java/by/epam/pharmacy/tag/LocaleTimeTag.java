package by.epam.pharmacy.tag;

import by.epam.pharmacy.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

@SuppressWarnings("serial")
public class LocaleTimeTag extends TagSupport {
    private static Logger logger = LogManager.getLogger();
    private static final String MESSAGE_TIME = "message.time";
    private static final String MESSAGE_LOCALE = "message.locale";
    private String locale;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {

        String time = "<hr/>" + ResourceManager.INSTANCE.getString(MESSAGE_TIME) + " <b> "
                + Calendar.getInstance(Locale.getDefault()).getTime() + " </b><hr/>";
        String region = null;
        if (null != locale) {
            region = ResourceManager.INSTANCE.getString(MESSAGE_LOCALE) + " <b> " + locale + " </b> ";
        } else {
            locale = Locale.getDefault().stripExtensions().toString();
            region = ResourceManager.INSTANCE.getString(MESSAGE_LOCALE) + " <b> " + locale + " </b> ";

        }
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
}