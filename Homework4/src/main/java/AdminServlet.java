import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.Journal;
import somePackage.JournalCatalogue;
import somePackage.User;
import utils.ConnectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static String CREATE = "INSERT INTO journal(name, genre, price) VALUES (?,?,?)";
    private PreparedStatement preparedStatement;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null || !((User) session.getAttribute("user")).isAdmin){
            response.sendRedirect("/");
        }else{
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String journalName = request.getParameter("journalName");
        String journalGenre = request.getParameter("journalGenre");
        Integer journalPrice = Integer.parseInt(request.getParameter("journalPrice"));

        try(Connection connection = ConnectionUtils.openConnection()){
            preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, journalName);
            preparedStatement.setString(2, journalGenre);
            preparedStatement.setInt(3, journalPrice);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException("Database error: " + e.getMessage());
        }

        JournalCatalogue.getJournalCatalogue().addJournal(new Journal(journalName, journalGenre, journalPrice));
    }
}
