import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.User;
import somePackage.UserService;
import utils.ConnectionUtils;

import java.io.IOException;
import java.sql.*;

public class RegistrationServlet extends HttpServlet {
    private static String CREATE = "INSERT INTO user(first_name, last_name, email, password) VALUES (?,?,?,?)";
    private static long serialVersionUID = 1L;
    private UserService userService = UserService.getUserService();

    private PreparedStatement preparedStatement;

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

        try(Connection connection = ConnectionUtils.openConnection()){
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        response.sendRedirect("/catalog");
    }
}
