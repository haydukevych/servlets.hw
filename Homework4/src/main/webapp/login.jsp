<%--
  Created by IntelliJ IDEA.
  User: Олесь
  Date: 16.01.2024
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>

<%--login.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/login" method="post">
        <%--@declare id="useremail"--%><%--@declare id="userpassword"--%>

        <label for="userEmail"> Email:</label>
        <input type="text" name="userEmail"><br>

        <label for="userPassword"> Password:</label>
        <input type="text" name="userPassword"><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
