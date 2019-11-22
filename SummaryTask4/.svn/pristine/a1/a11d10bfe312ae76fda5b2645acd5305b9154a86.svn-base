package ua.nure.chekhunov.summarytask4.servlets.order_room.admin_servlets;

import ua.nure.chekhunov.summarytask4.db.DBManager;
import ua.nure.chekhunov.summarytask4.db.order_room.Comment;
import ua.nure.chekhunov.summarytask4.db.order_room.Order;
import ua.nure.chekhunov.summarytask4.constants.OrderStatus;
import ua.nure.chekhunov.summarytask4.db.user.User;
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
 * Servlet for order page for admin.
 * Gets list of all comments to order.
 * Sends comments.
 *
 * @author IC
 */
@WebServlet(name = "OrderAdminServlet")
public class OrderAdminServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OrderAdminServlet.class);
    private static final String ORDER_ADMIN_JSP = "/jsp/admin/admin_order.jsp";
    private static final String ORDER_ADMIN_PAGE = "/admin_order";
    private static final String PROPERTY = "local";
    private static final int PER_PAGE = 10;

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_ADMIN_JSP).forward(req, resp);
    }

    /**
     * Sending comment or additional information about order.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (null != request.getParameter("actionName")) {
            String act = request.getParameter("actionName");
            int orderId = (int) request.getSession().getAttribute("order_id");
            if (act.equals("send_comment")) {
                try {

                    User user = (User) request.getSession().getAttribute("user");
                    int affectedRows = DBManager.getInstance().setOrderComment(orderId, request.getParameter("comment"),
                            user.getUserId());
                    boolean setCommentErr = checkResult(request, "order.send_comment.result_error.log",
                            affectedRows);
                    if (setCommentErr) {
                        request.setAttribute("error", true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", true);
                    LOG.error(e.getMessage());
                }
            } else if (act.equals("add_info")) {
                String addInfo = request.getParameter("additional_info");
                try {
                    DBManager.getInstance().setAddInfo(orderId, addInfo);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", true);
                    LOG.error(e.getMessage());
                }
            }
            response.sendRedirect(ORDER_ADMIN_PAGE);

        } else {

            fwd(request, response);
        }
    }

    /**
     * Displays list of all orders (by page).
     * Sets apartment to order and changes its status.
     * Sets order's id as session attribute (is will remain
     * the same while we are working with this order).
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId;
        try {
            Order order;
            if (null != request.getParameter("room_id")) {
                orderId = (int) request.getSession().getAttribute("order_id");
                int aptId = Integer.parseInt(request.getParameter("room_id"));
                int setAptRows = DBManager.getInstance().setOrdersApt(orderId, aptId);
                int setStatusRows = DBManager.getInstance().setOrderStatus(orderId, OrderStatus.IN_DISCUSSION);
                boolean errSetApt = checkResult(request, "order.set_apt_id.error.log", setAptRows);
                boolean errSetStatus = checkResult(request, "order.set_status.result_error.log", setStatusRows);
                if (errSetApt) {
                    request.setAttribute("error", true);
                }
                if (errSetStatus) {
                    request.setAttribute("error", true);
                }
            } else if (null == request.getSession().getAttribute("order_id") || null != request.getParameter("order_id")) {
                orderId = Integer.parseInt(request.getParameter("order_id"));
                request.getSession().setAttribute("order_id", orderId);
            } else {
                orderId = (int) request.getSession().getAttribute("order_id");
            }

            order = DBManager.getInstance().getOrder(orderId);
            request.setAttribute("order", order);
            int page = 1;
            if (null != request.getParameter("page")) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int offset = (page - 1) * PER_PAGE;
            List<Comment> commentList = DBManager.getInstance().getOrdersComments(orderId, PER_PAGE, offset);
            int numberOfComments = DBManager.getInstance().getNumberOfComments(orderId);
            int noOfPages = (int) Math.ceil(numberOfComments * 1.0 / PER_PAGE);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("comment_list", commentList);
            if (commentList.isEmpty()) {
                request.setAttribute("no_comments", true);
            }
        } catch (SQLException e) {
            request.setAttribute("error", true);
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        fwd(request, response);
    }

    /**
     * Checks if setting parameter to database was correct.
     * Number of affected rows must not be equal 0
     *
     * @param request
     * @param logKeyName - message to set in log
     * @param checkValue - checked result (must not be 0 if correct)
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
