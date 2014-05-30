<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Login Failed</title>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap-theme.css">	
</head>
<body>
	<%@ include file="navbar.jspf" %>
	<%@ include file="inline-login-form.jspf" %>	
	<div class="well alert">
		<h1>Login failed</h1>
		<p class="alert alert-danger">
			Your login attempt failed. Please try again, 
			or contact technical support for further assistance
		</p>
	</div>
</body>
</html>