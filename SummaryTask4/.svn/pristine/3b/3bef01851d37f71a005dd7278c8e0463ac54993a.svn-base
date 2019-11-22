package ua.nure.chekhunov.summarytask4.servlets.order_room;

import ua.nure.chekhunov.summarytask4.db.apartments.Apartment;
import ua.nure.chekhunov.summarytask4.db.apartments.LocalAptDescription;
import ua.nure.chekhunov.summarytask4.db.DBManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Servlet shows information about the room
 *
 * @author IC
 */
@WebServlet(name = "RoomDetailsServlet")
public class RoomDetailsServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(RoomDetailsServlet.class);

    private static final String ROOM_DETAILS_JSP = "/jsp/room.jsp";
    private static final String ROOM_DETAILS = "/room";
    private static void fwd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ROOM_DETAILS_JSP).forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getPathInfo().substring(1));
        response.sendRedirect(ROOM_DETAILS + "/" + String.valueOf(roomId));
    }

    /**
     * Gets room id from URI and sets Apartment object and
     * its description text as attribute.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = (int) request.getSession().getAttribute("order_id");
            int roomId = Integer.parseInt(request.getPathInfo().substring(1));
            Apartment apartment = DBManager.getInstance().getApt(roomId);
            Locale locale = (Locale) request.getSession().getAttribute("locale");
            LocalAptDescription description = DBManager.getInstance().getDescription(apartment.getDescription(), locale);
            request.setAttribute("room", apartment);
            request.setAttribute("description", description.getText());
            request.setAttribute("order_id", orderId);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", true);
            LOG.error(e.getMessage());
        }

        fwd(request, response);
    }
}
