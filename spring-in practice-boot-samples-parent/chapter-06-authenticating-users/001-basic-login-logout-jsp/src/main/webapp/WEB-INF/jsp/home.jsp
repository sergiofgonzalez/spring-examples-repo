<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:url var="homeUrl" value="/home" />
<c:url var="loginUrl" value="/login" />
<c:url var="logoutUrl" value="/logout" />
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Welcome Home</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap-theme.css">	
</head>
<body>
	<div class="navbar navbar-inverse">
		<a class="navbar-brand href="${homeUrl}">Home</a>
	</div>
	
	<div class="well">			
		<security:authorize access="isAnonymous()">
			Hi, guest. <a href="${loginUrl}">Log In</a>
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			Hi, <security:authentication property="principal.username" />.
			<a href="${logoutUrl}">Log out</a>
		</security:authorize>
	</div>
</body>
</html>