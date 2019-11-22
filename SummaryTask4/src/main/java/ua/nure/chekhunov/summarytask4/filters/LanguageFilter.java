package ua.nure.chekhunov.summarytask4.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Checks if language was not changed or set.
 *
 * @author ihor chekhunov
 */
@WebFilter(filterName = "LanguageFilter")
public class LanguageFilter implements Filter {

    public static final Logger LOG= Logger.getLogger(LanguageFilter.class);

    public void destroy() {
    }

    /**
     * Checks if language was not changed.
     * If Locale object is not set as session
     * attribute, sets it as english.
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String act = request.getParameter("actionName");
        Locale locale = (Locale) session.getAttribute("locale");
        String loc = request.getParameter("loc");
        if (locale != null) {
            if (act != null && act.equals("change_lang")) {
                Locale newLocale = new Locale(request.getParameter("locale"));
                session.setAttribute("locale", newLocale);
                chain.doFilter(req, resp);
            } else if (loc != null) {
                Locale locStartPage = new Locale(loc);
                session.setAttribute("locale", locStartPage);
                response.sendRedirect("/");
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            session.setAttribute("locale", new Locale("en"));
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
