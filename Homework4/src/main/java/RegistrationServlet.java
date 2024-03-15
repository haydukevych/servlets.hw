import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.User;
import somePackage.UserService;

import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private static long serialVersionUID = 1L;
    private UserService userService = UserService.getUserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Server at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("userName");
        String lastName = request.getParameter("userLastName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");

        User user = new User(firstName, lastName, email, password);
        userService.saveUser(user);

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        response.sendRedirect("/catalog");
    }
}
