<%@ page import="somePackage.JournalCatalogue" %>
<%@ page import="somePackage.Journal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Олесь
  Date: 15.03.2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Welcome to the cabinet buddy </h4>
    <form action="admin" method="post">
        <%--@declare id="journalname"--%><%--@declare id="journalgenre"--%><%--@declare id="journalprice"--%>

        <label for="journalName"> Journal Name:</label>
        <input type="text" name="journalName"><br>

        <label for="journalGenre"> Journal Genre:</label>
        <input type="text" name="journalGenre"><br>

        <label for="journalPrice"> Journal Price: </label>
        <input type="number" name="journalPrice"><br>

        <input type="submit" value="SUBMIT">

    </form>
</body>
</html>
