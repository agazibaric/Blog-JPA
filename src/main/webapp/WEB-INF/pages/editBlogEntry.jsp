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

	<c:if test="${sessionScope.currentUserId!=null}">
	
	<form action="edit" method="post" id="blogform">
		<div>
		 <div>
		  <span class="formLabel">Blog title</span>
		  <input type="text" name="title" value='<c:out value="${form.title}"/>' size="20">
		 </div>
		 <c:if test="${form.hasError('title')}">
		 <div class="error"><c:out value="${form.getError('title')}"/></div>
		 </c:if>
		</div>

		<input type="hidden" name="blogID" value="${blogID}">
		<input type="hidden" name="nick" value="${nick}">
		</form>
		
		<div>
		 <div>
		  <span class="formLabel">Text</span>
		  <textarea rows="20" cols="60" name="text" form="blogform">
		  	<c:out value="${form.text}"/>
		  </textarea>
		 </div>
		 <c:if test="${form.hasError('text')}">
		 <div class="error"><c:out value="${form.getError('text')}"/></div>
		 </c:if>
		</div>
		
		
		<p><input type="submit" value="Edit blog" form="blogform"></p>
		
		<p>
			Currently logged in as
			<c:out value="${sessionScope.currentUserFn} ${sessionScope.currentUserLn}"></c:out>
		</p>
		<a href="logout">Logout</a>
		
		<p><a href="main">HOME PAGE</a></p>

	</c:if>
</body>
</html>