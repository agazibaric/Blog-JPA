<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>

	<h1>
		<c:out value="${blog.title}"/>
	</h1>
	
	<p>
	<c:out value="${blog.text}"/>
	</p>
	
	<div>
	<h4>Comment section</h4>
		<c:forEach var="comment" items="${blog.comments}">
			<ul style="list-style-type: square">
				<li><c:out value="${comment.message}"></c:out></li>
				<c:out value="Posted by:  ${comment.usersEMail} | "/>
				<c:out value="Posted on:  ${comment.postedOn.toString()}"/>
			</ul>
		</c:forEach>
	</div>

	
		<form action="../../addComment" method="post">
			<p>
				Add new comment: <input type="text" name="message" size="50" required>
			</p>
			<c:if test="${sessionScope.currentUserId==null}">
				<p>
					Your email: <input type="text" name="email" size="50" required>
				</p>
			</c:if>
			<input type="hidden" name="blogID" value="${blog.id}"> 
			<input type="hidden" name="nick" value="${nick}">
			<input type="submit" value="Comment">
		</form>
	

	<c:set var="loggedNick" value="${sessionScope.currentUserNick}"/>
	<c:set var="blogNick" value="${nick}"/>
	<c:if test="${loggedNick eq blogNick}">
		<p>Edit blog <a href="../../author/${nick}/edit/${blog.id}">here</a></p>
	</c:if>
	
	<c:if test="${sessionScope.currentUserId!=null}">
		<p>
			Currently logged in as
			<c:out value="${sessionScope.currentUserFn} ${sessionScope.currentUserLn}"></c:out>
		</p>
		<a href="../../logout">Logout</a>
	</c:if>
	
	<p><a href="../../main">HOME PAGE</a></p>
	
</body>
</html>