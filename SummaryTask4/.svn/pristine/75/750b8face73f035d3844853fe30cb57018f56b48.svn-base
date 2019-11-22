package ua.nure.chekhunov.summarytask4.tag;

import ua.nure.chekhunov.summarytask4.db.DBManager;
import ua.nure.chekhunov.summarytask4.constants.AccessLevel;
import ua.nure.chekhunov.summarytask4.db.user.User;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Tag gets user's or admin's id and writes
 * "Administrator" + name if user is admin and
 * login otherwise.
 * @author ihor chekhunov
 */
public class UsernameTagHandler extends TagSupport {
    private static final Logger LOG = Logger.getLogger(UsernameTagHandler.class);

    private int userId;
    private Locale lang;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            User user = DBManager.getInstance().getUser(userId);

            if (user.getAccessLevel().equals(AccessLevel.ADMIN)) {
                String userName = user.getName();
                ResourceBundle resourceBundle = ResourceBundle.getBundle("local", lang);
                String admin = resourceBundle.getString("admin.order_page.admin_name");
                out.print(admin+ " " + userName);
            } else {
                String userLogin = user.getLogin();
                out.print(userLogin);
            }
        } catch (SQLException | LoginException | IOException  e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }

        return SKIP_BODY;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Locale getLang() {
        return lang;
    }

    public void setLang(Locale lang) {
        this.lang = lang;
    }

}
