import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import somePackage.Journal;
import somePackage.JournalCatalogue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BasketServlet")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("basket.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("submit") != null && (Boolean.valueOf(request.getParameter("submit")))){

            request.getSession().setAttribute("journals", new ArrayList<>());
        }
        else{
            String journalName = request.getParameter("journalName");
            Journal journal = JournalCatalogue.getJournalCatalogue().findJournalByName(journalName);
            HttpSession session = request.getSession(true);

            List<Journal> journals = (List<Journal>)session.getAttribute("journals");
            journals.remove(journal);

            session.setAttribute("journals", journals);
            request.getRequestDispatcher("basket.jsp").forward(request, response);
        }
    }
}
