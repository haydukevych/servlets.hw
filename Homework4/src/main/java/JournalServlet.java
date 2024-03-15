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

@WebServlet("/JournalServlet")
public class JournalServlet extends HttpServlet {

    private JournalCatalogue journalCatalogue = JournalCatalogue.getJournalCatalogue();

    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String journalName = request.getParameter("journalName");
        Journal journal = journalCatalogue.findJournalByName(journalName);
        HttpSession session = request.getSession(true);

        List<Journal> journals = session.getAttribute("journals") != null ? (List<Journal>) session.getAttribute("journals") : new ArrayList<>();
        journals.add(journal);

        session.setAttribute("journals", journals);
        request.getRequestDispatcher("journalCatalogue.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("journalCatalogue", journalCatalogue);
        request.getRequestDispatcher("journalCatalogue.jsp").forward(request, response);
    }
}
