package ua.nure.chekhunov.summarytask4.servlets.order_room.user_servlets;

import ua.nure.chekhunov.summarytask4.db.apartments.Apartment;
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
 * Servlet for order page for user.
 * Gets list of all comments to order.
 * Sends comments.
 *
 * @author IC
 */
@WebServlet(name = "OrderUserServlet")
public class OrderUserServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(OrderUserServlet.class);
    private static final String ORDER_USER_JSP = "/jsp/user/user_order.jsp";
    private static final String ORDER_USER_PAGE = "/user_order";
    private static final int PER_PAGE = 10;
    private static final String PROPERTY = "local";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_USER_JSP).forward(req, resp);
    }

    /**
     * Sends comments
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (null != request.getParameter("actionName")) {
            int orderId = (int) request.getSession().getAttribute("order_id");
            String act = request.getParameter("actionName");
            if (act.equals("send_comment")) {
                try {
                    User user = (User) request.getSession().getAttribute("user");
                    int affectedRows = DBManager.getInstance().setOrderComment(orderId, request.getParameter("comment"),
                            user.getUserId());
                    boolean errorRes = checkResult(request, "order.send_comment.result_error.log", affectedRows);
                    if (errorRes) {
                        request.setAttribute("error", true);
                        fwd(request, response);
                    } else {
                        response.sendRedirect(ORDER_USER_PAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", true);
                    LOG.error(e.getMessage());
                    fwd(request, response);
                }

            } else if (act.equals("change_lang")) {
                response.sendRedirect(ORDER_USER_PAGE);
            } else {
                fwd(request, response);
            }
        } else {
            fwd(request, response);
        }
    }

    /**
     * Gets comments, approves order and changes its
     * status when user approves order.
     * Sets order's id as session attribute (is will
     * remain the same while we are working with this order).
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId;
        if (null != request.getParameter("order_id")) {
            orderId = Integer.parseInt(request.getParameter("order_id"));
        } else {
            orderId = (int) request.getSession().getAttribute("order_id");
        }

        if (null != request.getParameter("actionName")) {
            String act = request.getParameter("actionName");
            if (act.equals("approve")) {
                try {
                    int setStatusRows = DBManager.getInstance().setOrderStatus(orderId, OrderStatus.APPROVED);
                    int setCostRows = DBManager.getInstance().setOrderCost(orderId);
                    request.setAttribute("order", DBManager.getInstance().getOrder(orderId));
                    boolean statusErr = checkResult(request, "order.set_status.result_error.log", setStatusRows);
                    boolean costErr = checkResult(request, "order.set_cost.error.log", setCostRows);
                    if (statusErr || costErr) {
                        request.setAttribute("error", true);
                    }
                } catch (SQLException e) {
                    request.setAttribute("error", e.getMessage());
                    LOG.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        try {
            Order order = DBManager.getInstance().getOrder(orderId);
            if (!order.getStatus().equals(OrderStatus.REQUESTED) || !(order.getStatus().equals(OrderStatus.REJECTED))) {
                Apartment apartment = DBManager.getInstance().getApt(order.getOrderedAptId());
                request.setAttribute("apt", apartment);
            }
            request.getSession().setAttribute("order_id", orderId);
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
     * Number of affected rows must not be equal to 0
     *
     * @param request
     * @param keyLogName - message to set in log
     * @param checkedValue - checked result (must not be 0 if correct)
     * @return true if error occurred
     */
    private boolean checkResult(HttpServletRequest request, String keyLogName, int checkedValue) {
        if (checkedValue == 0) {
            Locale locale = (Locale) request.getSession().getAttribute("locale");
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
            String logErr = resourceBundle.getString(keyLogName);
            LOG.error(logErr + " " + String.valueOf(checkedValue));
        }
        return (checkedValue == 0);
    }
}
