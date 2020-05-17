<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">

.error {
	font-family: fantasy;
	font-weight: bold;
	font-size: 0.9em;
	color: #FF0000;
	padding-left: 110px;
}

.formLabel {
	display: inline-block;
	width: 100px;
	font-weight: bold;
	text-align: right;
	padding-right: 10px;
}

</style>
</head>

<body>

<h1>Welcome to the BLOG</h1>

	<c:choose>
		<c:when test="${sessionScope.currentUserId==null}">
			<h2>Login</h2>
		
		<form action="main" method="post">
			<div>
				<div>
					<span class="formLabel">Nick name:</span> 
					<input type="text" name="nick" value='<c:out value="${form.nick}"/>' size="20">
				</div>
				<c:if test="${form.hasError('nick')}"></c:if>
				<div class="error">
					<c:out value="${form.getError('nick')}" />
				</div>
			</div>

			<div>
				<div>
					<span class="formLabel">Password:</span> 
					<input type="text" name="password" size="20">
				</div>
				<c:if test="${form.hasError('password')}"></c:if>
				<div class="error">
					<c:out value="${form.getError('password')}" />
				</div>
			</div>
			<input type="submit" value="Login">
		</form>

			<h2>Register</h2>
			<a href="register">Here</a>
			
		</c:when>
		<c:otherwise>

		<p>
			Currently logged in as
			<c:out value="${sessionScope.currentUserFn} ${sessionScope.currentUserLn}"></c:out>
		</p>
		<a href="logout">Logout</a>
		
		</c:otherwise>
	</c:choose>
	
	<h3>Users who registered on this blog:</h3>
	<c:forEach var="user" items="${registeredUsers}">
		<ul style="list-style-type: square">
			<li><a href="author/${user.nick}">${user.nick}</a></li>
		</ul>
	</c:forEach>
	
</body>

</html>