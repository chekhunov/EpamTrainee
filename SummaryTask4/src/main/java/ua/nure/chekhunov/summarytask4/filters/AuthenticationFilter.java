package ua.nure.chekhunov.summarytask4.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter checks if user is logged in.
 *
 * @author IC
 */
@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    public static final Logger LOG= Logger.getLogger(AuthenticationFilter.class);
    private static final String AUTH_PAGE = "/auth_error.jsp";
    private static final String LOGIN_PAGE = "/authentication";
    private static final String REG_PAGE = "/registration";
    private static final String START_PAGE = "/";
    private static final String INDEX_PAGE = "/index";
    List<String> noRegNeeded;


    public void destroy() {
    }

    /**
     * Redirects not authorised users from all pages,
     * except of login, register, start and page with
     * authentication error message to this page.
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String URI = request.getRequestURI();
        LOG.info(URI);
        boolean isNoRegNeeded = false;
        for (String s : noRegNeeded) {
            if (URI.contains(s) && (!s.equals("/")) || noRegNeeded.contains(URI)) {
                isNoRegNeeded = true;
            }
        }
        if (URI.indexOf("css") > 0){
            chain.doFilter(req, resp);
        }
        else if(URI.indexOf("/images") > 0){
            chain.doFilter(req, resp);
        }
        else if(URI.indexOf("/js") > 0){
            chain.doFilter(req, resp);
        }
        else if(URI.indexOf("favicon.ico") > 0) {
            chain.doFilter(req, resp);
        }
        else if (!isNoRegNeeded && (session == null || session.getAttribute("user") == null)) {
            response.sendRedirect(AUTH_PAGE);
        } else {
            chain.doFilter(req, resp); // Зарегистрированный пользователь найден или рег не требуется
        }
    }

    /**
     * Sets up list of ignored by filter pages
     * @param config
     * @throws ServletException
     */
    public void init(FilterConfig config) throws ServletException {
        noRegNeeded = new ArrayList<>(5);
        noRegNeeded.add(START_PAGE);
        noRegNeeded.add(REG_PAGE);
        noRegNeeded.add(LOGIN_PAGE);
        noRegNeeded.add(AUTH_PAGE);
        noRegNeeded.add(INDEX_PAGE);
    }

}
