package ua.nure.chekhunov.summarytask4.servlets.order_room;

import ua.nure.chekhunov.summarytask4.db.DBManager;
import ua.nure.chekhunov.summarytask4.constants.AccessLevel;
import ua.nure.chekhunov.summarytask4.db.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Servlet removes order after user confirms removing.
 *
 * @author IC
 */
@WebServlet(name = "RemoveOrderServlet")
public class RemoveOrderServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RemoveOrderServlet.class);
    private static final String REMOVING_JSP = "/jsp/warnings/remove_warning.jsp";
    private static final String USER_JSP = "/user";
    private static final String ADMIN_JSP = "/admin";
    private static final String PROPERTY = "local";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(REMOVING_JSP).forward(req, resp);
    }

    /**
     * Removes order.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId;
        String act = request.getParameter("actionName");
        if (act.equals("confirmed")) {
            orderId = (int) request.getSession().getAttribute("order_id");
            try {
                int removedRows = DBManager.getInstance().removeOrder(orderId);
                User user = (User) request.getSession().getAttribute("user");
                if (removedRows != 0) {
                    if (user.getAccessLevel().equals(AccessLevel.ADMIN)) {
                        response.sendRedirect(ADMIN_JSP);
                    } else {
                        response.sendRedirect(USER_JSP);
                    }
                } else {
                    Locale locale = (Locale) request.getSession().getAttribute("locale");
                    ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
                    String logErr = resourceBundle.getString("order.remove.error.log");
                    LOG.error(logErr + " " + String.valueOf(removedRows));
                    request.setAttribute("error", true);
                    fwd(request, response);
                }
            }
            catch (SQLException e) {
                request.setAttribute("error", true);
                LOG.error(e.getMessage());
                fwd(request, response);
            }
        }
        else if (act.equals("remove_order")){
            orderId = Integer.parseInt(request.getParameter("order_id"));
            request.getSession().setAttribute("order_id", orderId);
            fwd(request, response);
        } else {
            fwd(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fwd(request, response);
    }
}
