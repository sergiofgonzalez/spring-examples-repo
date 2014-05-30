<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>New User Registration</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>	
	<%@ include file="../navbar.jspf" %>
	<div class="well">
		<h1>New User Registration</h1>
		<form:form action="." modelAttribute="account">
			<div class="form-group">
				<label>Username</label>
				<form:input path="username" class="form-control" placeholder="your username"/> 
			</div>
			<div class="form-group">
				<label>Password</label>
				<form:password path="password" class="form-control" /> 
			</div>
			<div class="form-group">
				<label>Confirm Password</label>
				<form:password path="confirmPassword" class="form-control" /> 
			</div>
			<div class="form-group">
				<label>First Name</label>
				<form:input path="firstName" class="form-control" /> 
			</div>
			<div class="form-group">
				<label>Last Name</label>
				<form:input path="lastName" class="form-control" /> 
			</div>
			<div class="form-group">
				<label>E-mail Address</label>
				<form:input path="email" class="form-control" /> 
			</div>
			<div class="checkbox">
				<label>
					<form:checkbox path="marketingOk" /> Please send me product updates by e-mail.
				</label> 
			</div>
			<div class="checkbox">
				<label>
					<form:checkbox path="acceptTerms" /> I accept the <a href="#">terms of use</a>.
				</label> 
			</div>			
			<input type="submit" value="Submit" class="btn btn-default" />
		</form:form>
	</div>	
</body>
</html>