<%@ page import="java.util.List" %>
<%@ page import="somePackage.Journal" %>
<%@ page import="somePackage.JournalCatalogue" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Олесь
  Date: 16.01.2024
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>

<%--journalCatalogue.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Journals</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <h1>Welcome to the cabinet <%=request.getSession().getAttribute("userEmail") %>
    </h1>

    <h4>Available Journals:</h4>
    <%
        JournalCatalogue journalCatalogue = (JournalCatalogue) request.getAttribute("journalCatalogue");
        List<Journal> journalList = (journalCatalogue != null) ? journalCatalogue.getJournalList() : new ArrayList<>();
    %>
        <%
            for (Journal journal : journalList) {
        %>
        <div style="float: left; padding-left: 10px; margin-bottom: 10px; margin-left: 10px; width: 15%;
        border: 1px solid black; border-radius: 2px;
        display: flex; flex-direction: column">
            <p>Name: <%= journal.getName() %> </p>
            <p>Genre: <%= journal.getGenre() %></p>
            <p>Price: <%= journal.getPrice() %></p>
            <div style="width: 90%; display: flex; justify-content: right; padding-bottom: 15px">
                <button style="width: 60px;" onclick="buyJournalClicked('<%= journal.getName() %>')">Buy</button>
            </div>
        </div>
        <%
            }
        %>

</body>


<script>
        function buyJournalClicked(name) {

            alert("you are buying: " + name + " journal");

            $.ajax({
                type: "POST",
                url: "/JournalServlet", // Replace this with your servlet URL
                data: {
                    journalName: name
                },
                success: function (response) {
                    // Handle the response from the servlet
                    console.log(response);
                },
                error: function (xhr, status, error) {
                    console.error(status + ": " + error);
                }
            });
        }
</script>

</html>
