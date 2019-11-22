package ua.nure.chekhunov.summarytask4.servlets.order_room.user_servlets;

import ua.nure.chekhunov.summarytask4.db.DBManager;
import ua.nure.chekhunov.summarytask4.db.order_room.Order;
import ua.nure.chekhunov.summarytask4.db.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Editing order servlet
 *
 * @author IC
 */
@WebServlet(name = "EditingServlet")
public class EditingServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(EditingServlet.class);
    private static final String EDITING_JSP = "/jsp/user/edit_order.jsp";
    private static final String USER_JSP = "/user";
    private static final String PROPERTY = "local";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(EDITING_JSP).forward(req, resp);
    }

    /**
     * Gets new order parameters and rewrites it with new ones
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserId();

        int orderId = (int) request.getSession().getAttribute("order_id");
        String act = request.getParameter("actionName");
        if (null != request.getParameter("actionName")) {
            if (act.equals("submit_edit")) {
                int places = Integer.parseInt(request.getParameter("places"));
                int classOfComfort = Integer.parseInt(request.getParameter("class"));
                String comment = request.getParameter("comment");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OrderFormServlet.FORMATTER_PATTERN);
                String date_in = request.getParameter("date_in");
                String date_out = request.getParameter("date_out");
                LocalDate dateIn = LocalDate.parse(date_in, formatter);
                LocalDate dateOut = LocalDate.parse(date_out, formatter);
                if (dateIn.isAfter(LocalDate.now()) && dateIn.isBefore(dateOut)) {
                    try {
                        int affectedRows = DBManager.getInstance().editOrder(orderId, userId, places, classOfComfort, dateIn, dateOut, comment);
                        if (affectedRows != 0) {
                            response.sendRedirect(USER_JSP);
                        } else {
                            Locale locale = (Locale) request.getSession().getAttribute("locale");
                            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
                            String logErr = resourceBundle.getString("order.edit.result_error.log");
                            request.setAttribute("error", true);
                            LOG.error(logErr + " " + String.valueOf(affectedRows));
                            fwd(request, response);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("order_error", e.getMessage());
                        LOG.error(e.getMessage());
                        fwd(request, response);
                    }
                } else {
                    request.setAttribute("order_error", true);
                    fwd(request, response);
                }
            } else {
                try {
                    Order order = DBManager.getInstance().getOrder(orderId);
                    request.setAttribute("order", order);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", true);
                    LOG.error(e.getMessage());
                }

                fwd(request, response);
            }

        } else {
            fwd(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fwd(request, response);
    }
}
