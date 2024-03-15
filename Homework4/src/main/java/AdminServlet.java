import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.Journal;
import somePackage.JournalCatalogue;
import somePackage.User;

import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
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

        JournalCatalogue.getJournalCatalogue().addJournal(new Journal(journalName, journalGenre, journalPrice));
    }
}
