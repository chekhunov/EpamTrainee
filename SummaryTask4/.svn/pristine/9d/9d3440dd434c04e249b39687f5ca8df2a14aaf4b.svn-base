package ua.nure.chekhunov.summarytask4.servlets.order_room.admin_servlets;

import ua.nure.chekhunov.summarytask4.db.apartments.Apartment;
import ua.nure.chekhunov.summarytask4.db.DBManager;
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
 * Servlet gets all rooms suitable to users requirements.
 *
 * @author IC
 */
@WebServlet(name = "SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SearchRoomServlet.class);
    private static final String SEARCH_ROOM_JSP = "/jsp/admin/search_room.jsp";
    private static final int PER_PAGE = 10;
    private static final String PROPERTY = "local";

    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(SEARCH_ROOM_JSP).forward(req, resp);
    }

    /**
     * Gets suitable rooms list (with paging parameters)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = (int) request.getSession().getAttribute("order_id");
            int page = 1;
            if (null != request.getParameter("page")) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int offset = (page - 1) * PER_PAGE;

            List<Apartment> res = DBManager.getInstance().getSuitableApts(orderId,
                    PER_PAGE, offset);
            int numberOfOrders = DBManager.getInstance().getAptNumbers(orderId);
            int noOfPages = (int) Math.ceil(numberOfOrders * 1.0 / PER_PAGE);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("result_list", res);

            if (res.isEmpty()) {
                request.setAttribute("no_result", true);
            }
            fwd(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", true);
            LOG.error(e.getMessage());
            fwd(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Locale locale = (Locale) request.getSession().getAttribute("locale");
            ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTY, locale);
            LOG.error(resourceBundle.getString("search_room.error.log"));
            fwd(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
