import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class WebServer extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>WebServer Testing</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");
        out.println("This is a test of our server");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}