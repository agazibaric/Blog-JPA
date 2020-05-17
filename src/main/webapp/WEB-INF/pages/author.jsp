<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Author's blogs</title>
</head>
<body>
	
	<c:forEach var="blog" items="${blogs}"> 
		<ul style="list-style-type: square">
			<li><a href="${nick}/${blog.id}">${blog.title}</a></li>
		</ul>
	</c:forEach>
	
	<c:set var="loggedNick" value="${sessionScope.currentUserNick}"/>
	<c:set var="blogNick" value="${nick}"/>
	<c:if test="${loggedNick eq blogNick}">
		<p>Add new blog to your collection <a href="${nick}/new">here</a></p>
	</c:if>
	
	<c:if test="${sessionScope.currentUserId!=null}">
		<p>
			Currently logged in as
			<c:out value="${sessionScope.currentUserFn} ${sessionScope.currentUserLn}"></c:out>
		</p>
		<a href="../logout">Logout</a>
	</c:if>

	<p><a href="main">HOME PAGE</a></p>
	
</body>
</html>
