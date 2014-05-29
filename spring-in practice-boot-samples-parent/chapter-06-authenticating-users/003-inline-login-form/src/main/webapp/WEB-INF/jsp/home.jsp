<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
	<%@ include file="navbar.jspf" %>
	<%@ include file="inline-login-form.jspf" %>
	
	<div class="well">
		<security:authorize access="isAuthenticated()">
			This content is only available to authenticated users like you, 
			<security:authentication property="principal.username" />.			
		</security:authorize>
		<security:authorize access="isAnonymous()">
			Hi, guest. Log In to see <a href="/loginRequired">content</a> only available to authenticated users.
		</security:authorize>		
	</div>
</body>
</html>