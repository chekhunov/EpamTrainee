package ua.nure.chekhunov.practice9.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CalcServlet() {
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet CalcServlet</title>");
		out.println("</head>");
		out.println("<body>");
		try {
			int x = Integer.valueOf(request.getParameter("x"));
			int y = Integer.valueOf(request.getParameter("y"));
			String operation = request.getParameter("op");
			out.println("<p>Result: " + calc(x, y, operation) + "</p>");
		} catch (NumberFormatException e) {
			out.println("<h1>Неверные данные!</h1>");
		} finally {
			out.println("<a href = \"http://localhost:8080/Practice9/Calc.html\">Назад</a><br />");
			out.println("<a href = \"http://localhost:8080/Practice9/\">На главную</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	private int calc(int x, int y, String operation) {
		if ("+".equals(operation)) {
			return x + y;
		} else if ("-".equals(operation)) {
			return x - y;
		} else {
			throw new NumberFormatException();
		}
	}
}
