<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="homeUrl" value="/home" />
<c:url var="postLoginUrl" value="/j_spring_security_check" />
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Log In</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap-theme.css">	
</head>
<body>
	<div class="navbar navbar-inverse">
		<a class="navbar-brand" href="${homeUrl}">Home</a>
	</div>
	
	<div class="well">
		<c:if test="${!param.failed}">
			<div class="alert alert-info">			
				Please Log In						
			</div>
		</c:if>
		<form action="${postLoginUrl}" method="post">
			<c:if test="${param.failed == true}">
				<div class="alert alert-danger">
					Your login attempt failed. Please try again, or contact technical support
					for further assistance.
				</div>
			</c:if>
			<div class="form-group">
				<label>Username</label>
				<input type="text" name="j_username" class="form-control" placeholder="your username"/>
			</div>
			<div class="form-group">
				<label>Password</label>
				<input type="password" name="j_password" class="form-control" placeholder="your password"/>
			</div>
			<div class="checkbox">
				<label>
					<input type="checkbox" name="_spring_security_remember_me" />
					Remember Me
				</label>
			</div>
			<div class="text-center">
				<input type="submit" value="Log In" class="btn btn-primary" />
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>		
	</div>
</body>
</html>