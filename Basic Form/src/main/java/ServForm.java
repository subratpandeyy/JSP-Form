import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServForm")
public class ServForm extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        // Print in browser
        pw.println("<html><body>");
        pw.println("<h2>Form Submission Result</h2>");
        pw.println("<p>Name: " + name + "</p>");
        pw.println("<p>Email: " + email + "</p>");
        pw.println("<p>Age: " + age + "</p>");
        pw.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
