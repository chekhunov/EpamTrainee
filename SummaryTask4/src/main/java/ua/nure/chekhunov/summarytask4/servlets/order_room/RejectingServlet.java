package ua.nure.chekhunov.summarytask4.servlets.order_room;

import ua.nure.chekhunov.summarytask4.db.DBManager;
import ua.nure.chekhunov.summarytask4.constants.OrderStatus;
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
 * Servlet sets status of order to REJECTED when
 * user or admin confirms rejecting.
 *
 * @author IC
 */
@WebServlet(name = "RejectingServlet")
public class RejectingServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RejectingServlet.class);
    private static final String REJECTING_JSP = "/jsp/warnings/reject_warning.jsp";
    private static final String USER_JSP = "/user";
    private static final String ADMIN_JSP = "/admin";
    private static final String PROPERTY = "local";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(REJECTING_JSP).forward(req, resp);
    }

    /**
     * Rejects order
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("actionName");
        int orderId = (int) request.getSession().getAttribute("order_id");
        if (act.equals("reject")) {
            fwd(request, response);
        } else if (act.equals("confirmed")) {
            try {
                User user = (User) request.getSession().getAttribute("user");
                boolean correct = rejectOrder(orderId, request);
                if (correct) {
                    if (user.getAccessLevel().equals(AccessLevel.ADMIN)) {
                        response.sendRedirect(ADMIN_JSP);
                    } else {
                        response.sendRedirect(USER_JSP);
                    }
                } else {
                    request.setAttribute("error", true);
                    fwd(request, response);
                }

            } catch (SQLException e) {
                request.setAttribute("error", true);
                LOG.error(e.getMessage());
                fwd(request, response);
            }
        } else {
            fwd(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fwd(request, response);
    }

    /**
     * @param orderId
     * @param request
     * @return if error occurred
     * @throws SQLException
     */
    private boolean rejectOrder(int orderId, HttpServletRequest request) throws SQLException {
        int statusRows = DBManager.getInstance().setOrderStatus(orderId, OrderStatus.REJECTED);
        int dropAptRows = DBManager.getInstance().setOrdersApt(orderId, 0); // drop apt_id
        int dropCostRows = DBManager.getInstance().setNullCost(orderId);
        boolean statusErr = checkResult(request, "order.set_status.result_error.log", statusRows);
        boolean setAptErr = checkResult(request, "order.drop_apt.error.log", dropAptRows);
        boolean dropCostErr = checkResult(request, "order.drop_cost.error.log", dropCostRows);
        return !(statusErr || setAptErr || dropCostErr);
    }

    /**
     * Checks if setting parameter to db was correct.
     * Number of affected rows must be equal 1
     *
     * @param request
     * @param logKeyName - message to set in log
     * @param checkValue - checked result (must be 1 if correct)
     * @return true if error occurred
     */
    private boolean checkResult(HttpServletRequest request, String logKeyName, int checkValue) {
        if (checkValue == 0) {
            Locale locale = (Locale) request.getSession().getAttribute("locale");
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
            String logErr = resourceBundle.getString(logKeyName);
            LOG.error(logErr + " " + String.valueOf(checkValue));
        }
        return (checkValue == 0);
    }
}
