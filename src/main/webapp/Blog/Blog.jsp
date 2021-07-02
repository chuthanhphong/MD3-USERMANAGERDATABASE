<%--
  Created by IntelliJ IDEA.
  User: Windboy
  Date: 02/07/2021
  Time: 2:56 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2>ListBlog</h2>
    <c:forEach var="blog" items="${listBlog}">
        <table border="1px">
        <tr>
        <td><c:out value="${blog.id}"/> </td>
        <td><c:out value="${blog.title}"/> </td>
        <td><c:out value="${blog.content}"/> </td>
        <td><c:out value="${blog.user_id}"/> </td>
        </tr>
        </table>
    </c:forEach>
</body>
</html>