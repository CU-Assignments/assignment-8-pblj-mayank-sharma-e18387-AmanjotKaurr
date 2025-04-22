import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EasyServlet extends HttpServlet {

    // Handles POST requests from login form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check credentials (hardcoded)
        if ("admin".equals(username) && "1234".equals(password)) {
            // Valid credentials - display welcome message
            out.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            // Invalid credentials
            out.println("<h2>Invalid username or password.</h2>");
        }
    }
}
