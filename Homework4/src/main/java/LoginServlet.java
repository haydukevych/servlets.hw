import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.User;
import somePackage.UserService;


import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");

        UserService userService = UserService.getUserService();
        User user = userService.getUser(login);

        HttpSession session = request.getSession(true);

        if(user == null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else if (login.equals("admin") && password.equals("admin")) {
            user.isAdmin = true;
            session.setAttribute("user", user);
            response.sendRedirect("/admin");
        }else if (user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            response.sendRedirect("/catalog");
        }
    }
}
