import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SaveAttendanceServlet extends HttpServlet {

    // Handles POST requests from attendance form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load JDBC driver and connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_portal", "root", "password");

            // Insert attendance record
            String query = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setString(2, date);
            ps.setString(3, status);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<h2>Attendance recorded successfully!</h2>");
            } else {
                out.println("<h2>Failed to record attendance.</h2>");
            }

            // Close resources
            ps.close();
            conn.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
