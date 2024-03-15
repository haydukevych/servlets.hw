<%@ page import="java.util.List" %>
<%@ page import="somePackage.Journal" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Олесь
  Date: 14.03.2024
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

    <%
        List<Journal> journals = (List<Journal>) request.getSession().getAttribute("journals");
        if (journals == null){
            journals = new ArrayList<>();
        }
        Double totalPrice = 0d;
    %>
    <%
        for (Journal journal : journals) {
            totalPrice += journal.getPrice();
    %>
        <div style="float: left; padding-left: 10px; margin-bottom: 10px; margin-left: 10px; width: 15%;
            border: 1px solid black; border-radius: 2px;
            display: flex; flex-direction: column">
            <p>Name: <%= journal.getName() %> </p>
            <p>Genre: <%= journal.getGenre() %></p>
            <p>Price: <%= journal.getPrice() %></p>
            <div style="width: 90%; display: flex; justify-content: right; padding-bottom: 15px">
                <button style="width: 60px;" onclick="deleteJournalClicked('<%= journal.getName() %>')">Delete</button>
            </div>
        </div>
    <%
        }
    %>
    <h4> Total Price: $<%= totalPrice %> </h4>
    <button onclick="placeOrder()">SUBMIT</button>

    <script>
        function placeOrder(){
            $.ajax({
                type: "POST",
                url: "/BasketServlet", // Replace this with your servlet URL
                data: {
                    submit: true
                },
                success: function (response) {
                    // Handle the response from the servlet
                    alert("Thank you for buying!");
                    window.location.href = "/";
                },
                error: function (xhr, status, error) {
                    console.error(status + ": " + error);
                }
            });
        }

        function deleteJournalClicked(name) {

            alert("you are removing: " + name + " journal");

            $.ajax({
                type: "POST",
                url: "/BasketServlet", // Replace this with your servlet URL
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
</body>
</html>
