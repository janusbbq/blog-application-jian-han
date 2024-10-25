<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserPosts</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>PostId</th>
                <th>UserName</th>
                <th>Content</th>
                <th>Created</th>
                <th>Comments</th>
                <th>Delete Post</th>
            </tr>
            <c:forEach items="${posts}" var="post" >
                <tr>
                    <td><c:out value="${post.getPostId()}" /></td>
                    <td><c:out value="${post.getUserName()}" /></td>
                    <td><c:out value="${post.getContent()}" /></td>
                    <td><fmt:formatDate value="${post.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    <td><a href="userpostcomments?postid=<c:out value="${post.getPostId()}"/>">Comments</a></td>
                    <td><a href="deletepost?postid=<c:out value="${post.getPostId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
