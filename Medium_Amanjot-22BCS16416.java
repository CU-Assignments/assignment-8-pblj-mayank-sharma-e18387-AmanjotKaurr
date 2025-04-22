import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {

    // Handles GET requests to view/search employees
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("id"); // Get employee ID from request (optional)
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load JDBC driver and connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");

            // Prepare SQL query
            String query = (empId != null && !empId.isEmpty())
                ? "SELECT * FROM employee WHERE id = ?"
                : "SELECT * FROM employee";

            PreparedStatement ps = conn.prepareStatement(query);

            if (empId != null && !empId.isEmpty()) {
                ps.setInt(1, Integer.parseInt(empId));
            }

            ResultSet rs = ps.executeQuery();

            // Display employee data
            out.println("<h2>Employee Details</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>"
                        + rs.getString("name") + "</td><td>"
                        + rs.getString("department") + "</td></tr>");
            }

            out.println("</table>");

            // Close resources
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
