package ua.nure.chekhunov.summarytask4.servlets.admin;

import ua.nure.chekhunov.summarytask4.db.DBManager;
import ua.nure.chekhunov.summarytask4.db.order_room.Order;
import ua.nure.chekhunov.summarytask4.constants.OrderStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Displays list of all orders and orders
 * by their status on admin's page.
 *
 * @author ihor chekhunov
 */
@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AdminServlet.class);
    private static final String ADMIN_JSP = "/jsp/admin/admin.jsp";

    private static final int PER_PAGE = 10;
    private static final String PROPERTY = "local";

    /**
     * Forwards to current jsp page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
    }

    /**
     * Gets list of all orders and of orders by status.
     * Sets them partly as attribute
     * @see DBManager
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> ordersList;
        int page = 1;
        if (null != request.getParameter("page")) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int offset = (page - 1) * PER_PAGE;
        int numberOfOrders;
        OrderStatus status = null;
        Locale locale = (Locale) request.getSession().getAttribute("locale");
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
        String heading = resourceBundle.getString("admin.orders.heading.all");
        if (null != request.getParameter("actionName")) {
            String act = request.getParameter("actionName");
            switch (act) {
                case "requested":
                    status = OrderStatus.REQUESTED;
                    heading = resourceBundle.getString("admin.orders.heading.requested");
                    break;
                case "in_discussion":
                    status = OrderStatus.IN_DISCUSSION;
                    heading = resourceBundle.getString("admin.orders.heading.in_discussion");
                    break;
                case "approved":
                    status = OrderStatus.APPROVED;
                    heading = resourceBundle.getString("admin.orders.heading.approved");
                    break;
                case "paid":
                    status = OrderStatus.PAID;
                    heading = resourceBundle.getString("admin.orders.heading.paid");
                    break;
                case "rejected":
                    status = OrderStatus.REJECTED;
                    heading = resourceBundle.getString("admin.orders.heading.rejected");
                    break;
            }
        }
        try {
            if (null == status) {
                ordersList = DBManager.getInstance().getAllOrders(PER_PAGE, offset);
                numberOfOrders = DBManager.getInstance().getNumberOfAllOrders();
            } else {
                ordersList = DBManager.getInstance().getOrdersByStatus(PER_PAGE, offset, status);
                numberOfOrders = DBManager.getInstance().getNumberOfOrdersByStatus(status);
            }
            int noOfPages = (int) Math.ceil(numberOfOrders * 1.0 / PER_PAGE);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("heading", heading);
            request.setAttribute("orders_list", ordersList);
            if (ordersList.isEmpty()) {
                request.setAttribute("no_result", true);
            }
        } catch (SQLException e) {
            request.setAttribute("no_result", true);
            request.setAttribute("error", true);
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        fwd(request, response);

    }

    /**
     * Removes attribute order_id when is called from any
     * page connected with specified order
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (null != request.getSession().getAttribute("order_id")) {
            request.getSession().removeAttribute("order_id");
        }
        doPost(request, response);
    }

}
