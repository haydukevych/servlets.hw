import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.User;
import somePackage.UserService;
import utils.ConnectionUtils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static String READ_BY_EMAIL = "select * from user where email = ?";
    private static final long serialVersionUID = 1L;

    private PreparedStatement preparedStatement;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");

        UserService userService = UserService.getUserService();
        User user = userService.getUser(login);

        HttpSession session = request.getSession(true);

        try (Connection connection = ConnectionUtils.openConnection()){
            preparedStatement = connection.prepareStatement(READ_BY_EMAIL);
            preparedStatement.setString(1, login);

            try(ResultSet result = preparedStatement.executeQuery()){
                if(result.next()){
                    String emailSql = result.getString("email");
                    String passwordSql = result.getString("password");

                    if(login.equals(emailSql) && password.equals(passwordSql)){
                        session.setAttribute("user", user);
                        response.sendRedirect("/catalog");
                        return;
                    }
                } else if(user == null){
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if(login.equals("admin") && password.equals("admin")){
                    user.isAdmin = true;
                    session.setAttribute("user", user);
                    response.sendRedirect("/admin");
                }
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }
    }
}
