package ua.nure.chekhunov.practice9.calc;

	import java.io.IOException;
	import java.io.PrintWriter;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet(description = "Simple Two-Op Calculator", 
	            urlPatterns = { "/ServletCalc" })
	public class ServletCalc extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	       
	    public ServletCalc() {
	        super();
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");
	        try (PrintWriter out = response.getWriter();) {
	            outRes(request, out);
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	    }           
	    
	    private double calc(double x, double y, String op) {
	        if ("+".equals(op)) {
	            return (x + y);
	        } else if ("-".equals(op)) {
	            return (x - y);
	        } else {
	            throw new NumberFormatException();
	        }
	    }
	    
	    private void outRes(HttpServletRequest request, PrintWriter out) {
	        try {
	            String title = "ServletCalc";
	            
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>" + title + "</title>");
	            out.println("</head>");
	            out.println("<body>");
	            
	            double x = Double.parseDouble(request.getParameter("x"));
	            double y = Double.parseDouble(request.getParameter("y"));
	            String op = request.getParameter("op");
	            out.println("<p>Result: " + calc(x, y, op) + "</p>");
	            
	            out.println("<a href=\"http://localhost:8080/Practice9/calc.html\">" 
	                       + "New calculation" + "</a><br />");
	            out.println("<a href=\"http://localhost:8080/Practice9/\">" 
	                       + "Start page" + "</a>");
	            
	            out.println("</body>");
	            out.println("</html>");
	            
	        } catch (NumberFormatException ex) {
	            out.println("<h1>Data error!</h1>");
	        }
	    }
	}