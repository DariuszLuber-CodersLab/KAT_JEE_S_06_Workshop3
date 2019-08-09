<%--
  Created by IntelliJ IDEA.
  User: dariu
  Date: 09.08.2019
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Register</h1>
    <form action="/register" method="post">
        <input type="text" name="user" placeholder="Username" />
        <input type="text" name="mail" placeholder="eMail" />
        <input type="password" name="pass" placeholder="Password" />
        <input type="submit" />
    </form>
</body>
</html>
