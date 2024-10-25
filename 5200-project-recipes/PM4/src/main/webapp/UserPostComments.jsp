<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BlogComments</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>CommentId</th>
                <th>PostId</th>
                <th>UserName</th>
                <th>Content</th>
                <th>Created</th>
            </tr>
            <c:forEach items="${userComments}" var="comment" >
                <tr>
                
                    <td><c:out value="${comment.commentId}" /></td>
                    <td><c:out value="${comment.postId}" /></td>
                    <td><c:out value="${comment.userName}" /></td>
                    <td><c:out value="${comment.commentContent}" /></td>
                    <td><fmt:formatDate value="${comment.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>