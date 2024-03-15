<%--
  Created by IntelliJ IDEA.
  User: Олесь
  Date: 16.01.2024
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>

<%--registration.jsp--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/registration" method="post">
    <%--@declare id="username"--%><%--@declare id="userlastname"--%><%--@declare id="useremail"--%><%--@declare id="userpassword"--%>

    <label for="userName"> Name:</label>
    <input type="text" name="userName"> <br>

    <label for="userLastName"> Last Name:</label>
    <input type="text" name="userLastName"> <br>

    <label for="userEmail"> Email:</label>
    <input type="text" name="userEmail"><br>

    <label for="userPassword"> Password:</label>
    <input type="text" name="userPassword"><br>

    <input type="submit" value="Submit">

</form>
</body>
</html>
