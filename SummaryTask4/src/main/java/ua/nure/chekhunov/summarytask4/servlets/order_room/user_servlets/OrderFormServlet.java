package ua.nure.chekhunov.summarytask4.servlets.order_room.user_servlets;

import ua.nure.chekhunov.summarytask4.db.DBManager;
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
 * Servlet gets order parameters and sets new order.
 *
 * @author IC
 */
@WebServlet(name = "OrderFormServlet")
public class OrderFormServlet extends HttpServlet {
    private static final Logger LOG= Logger.getLogger(OrderFormServlet.class);
    private static final String ORDER_JSP = "/jsp/user/order.jsp";
    private static final String USER_PAGE = "/user";
    private static final String PROPERTY = "local";

    public static final String FORMATTER_PATTERN = "yyyy-MM-dd";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_JSP).forward(req, resp);
    }

    /**
     * Method gets order parameters and makes a new order with them if possible.
     * Checks if dates are set correct
     * Метод получает параметры заказа и делает новый заказ с ними, если это возможно.
     * Проверяет, правильно ли установлены даты
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act=request.getParameter("actionName");
        if (act.equals("order")) {
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            int places = Integer.parseInt(request.getParameter("places"));
            int classOfComfort = Integer.parseInt(request.getParameter("class"));
            String comment = request.getParameter("comment");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_PATTERN);
            String date_in = request.getParameter("date_in");
            String date_out = request.getParameter("date_out");
            LocalDate dateIn = LocalDate.parse(date_in, formatter);
            LocalDate dateOut = LocalDate.parse(date_out, formatter);
            if (dateIn.isAfter(LocalDate.now()) && dateIn.isBefore(dateOut)) {
                try {
                    int affectedRows = DBManager.getInstance().setNewOrder(userId, places, classOfComfort,
                            dateIn, dateOut, comment);
                    if (affectedRows == 0) {
                        Locale locale = (Locale) request.getSession().getAttribute("locale");
                        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
                        String logErr = resourceBundle.getString("order.set_order.error.log");
                        LOG.error(logErr + " " + String.valueOf(affectedRows));
                        request.setAttribute("error", true);
                        fwd(request, response);
                    } else {
                        response.sendRedirect(USER_PAGE);
                    }
                } catch (SQLException e) {
                    request.setAttribute("error", true);
                    LOG.error(e.getMessage());
                    fwd(request, response);
                }
            } else {
                request.setAttribute("order_error", true);
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
