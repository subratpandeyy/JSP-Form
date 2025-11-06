import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/FormServ")
public class FormServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phoneString = req.getParameter("phone");
        String genderString = req.getParameter("gender");
        String passwordString = req.getParameter("password");
        String ageString = req.getParameter("age");
        
        
        System.out.println(username);
        System.out.println(email);
        System.out.println(phoneString);
        System.out.println(genderString);
        System.out.println(passwordString);
        System.out.println(ageString);

        
        //jdbc
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/db1", "root", "root");
        	pw.println("DB connected");
        	String sqlString = "Insert into Student(name, email, phone, gender, pass, age) values(?, ?, ?, ?, ?, ?);";
        	PreparedStatement ps = conn.prepareStatement(sqlString);
        	ps.setString(1, username);
        	ps.setString(2, email);
        	ps.setString(3, phoneString);
        	ps.setString(4, genderString);
        	ps.setString(5, passwordString);
        	ps.setString(6, ageString);
        	
        	int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                pw.println("Data inserted successfully!");
            }
            
            ResultSet rs = ps.executeQuery("Select * from student;");
            while(rs.next()) {
            	String dbName = rs.getString("name");
            	String dbEmail = rs.getString("email");
            	String dbPhone = rs.getString("phone");
            	String dbGender = rs.getString("gender");
            	String dbPass = rs.getString("pass");
            	String dbAge = rs.getString("age");
            	
            	pw.println("<html><body>");
                pw.println("<h2>Form Submission Result</h2>");
                pw.println("<p>Name: " + dbName + "</p>");
                pw.println("<p>Email: " + dbEmail + "</p>");
                pw.println("<p>Phone: " + dbPhone + "</p>");
                pw.println("<p>Gender: " + dbGender + "</p>");
                pw.println("<p>Password: " + dbPass + "</p>");
                pw.println("<p>Age: " + dbAge + "</p>");
                pw.println("</body></html>");
            }

            ps.close();
            conn.close();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	pw.println(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
