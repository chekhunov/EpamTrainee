package ua.nure.chekhunov.summarytask4.servlets.authentication;

import ua.nure.chekhunov.summarytask4.constants.AccessLevel;
import ua.nure.chekhunov.summarytask4.db.user.User;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Servlet registers users if possible and redirects to user's page
 *
 * @author IC
 */
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);

    private static final String REGISTRATION_JSP = "/jsp/authentication/registration.jsp";
    private static final String LOGIN_JSP = "/authentication";
    private static final String USER_JSP = "/user";
    private static final String ADMIN_JSP = "/admin";

    private static final String PROPERTY = "local";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    /**
     * Creates new user if input data is correct
     * Logs user in
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String act = request.getParameter("actionName");
        if (act.equals("registration")) {
            try {
                User user = new User(name, login, password, email);
                request.getSession().setAttribute("user", user.logIn(login, password));
                response.sendRedirect(LOGIN_JSP);

            } catch (LoginException e) {
                String errorMsg;
                if (e.getMessage().equals("1")) {
                    errorMsg = resourceBundle.getString("registration.registration_error.already_exists");
                } else {
                    errorMsg = resourceBundle.getString("registration.registration_error.wrong_email");
                }
                request.setAttribute("reg_error", errorMsg);
                LOG.info(errorMsg);
                fwd(request, response);
            }

        } else {
            fwd(request, response);
        }
    }

    /**
     * Redirects from registration page to user's or admin's page if user is logged in
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getAccessLevel().equals(AccessLevel.ADMIN)) {
                response.sendRedirect(ADMIN_JSP);
            } else {
                response.sendRedirect(USER_JSP);
            }
        } else {
            fwd(request, response);
        }
    }

}
