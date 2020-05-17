<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>

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

.formControls {
	margin-top: 10px;
}
</style>
</head>

	<body>

		<form action="register" method="post">

		<div>
		 <div>
		  <span class="formLabel">First name</span>
		  <input type="text" name="firstname" value='<c:out value="${form.getFirstName()}"/>' size="20">
		 </div>
		 <c:if test="${form.hasError('firstname')}">
		 <div class="error"><c:out value="${form.getError('firstname')}"/></div>
		 </c:if>
		</div>

		<div>
		 <div>
		  <span class="formLabel">Last name</span>
		  <input type="text" name="lastname" value='<c:out value="${form.getLastName()}"/>' size="20">
		 </div>
		 <c:if test="${form.hasError('lastname')}">
		 <div class="error"><c:out value="${form.getError('lastname')}"/></div>
		 </c:if>
		</div>

		<div>
		 <div>
		  <span class="formLabel">Email</span>
		  <input type="text" name="email" value='<c:out value="${form.getEmail()}"/>' size="50">
		 </div>
		 <c:if test="${form.hasError('email')}">
		 <div class="error"><c:out value="${form.getError('email')}"/></div>
		 </c:if>
		</div>
		
		<div>
		 <div>
		  <span class="formLabel">Nick name</span>
		  <input type="text" name="nick" value='<c:out value="${form.getNick()}"/>' size="20">
		 </div>
		 <c:if test="${form.hasError('nick')}">
		 <div class="error"><c:out value="${form.getError('nick')}"/></div>
		 </c:if>
		</div>
		
		<div>
		 <div>
		  <span class="formLabel">Password</span>
		  <input type="text" name="password" value='<c:out value="${form.getPassword()}"/>' size="20">
		 </div>
		 <c:if test="${form.hasError('password')}">
		 <div class="error"><c:out value="${form.getError('password')}"/></div>
		 </c:if>
		</div>

		<input type="submit" value="Register">
		</form>

		<p><a href="main">HOME PAGE</a></p>
		
	</body>
</html>
