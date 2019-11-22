package ua.nure.chekhunov.practice10.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final String ENCODING = "UTF-8";

	private static final String CONTENT_TYPE = "text/html";

	public Controller() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding(ENCODING);
		request.setCharacterEncoding(ENCODING);

	}
}
