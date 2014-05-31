<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="pageTitle" code="newUserRegistration.pageTitle" />
<spring:message var="msgAllFieldsRequired" code="newUserRegistration.message.allFieldsRequired" />
<spring:message var="register" code="newUserRegistration.label.register" />
<spring:message var="usernamePlaceholder" code="newUserRegistration.placeholder.username" />
<spring:message var="firstNamePlaceholder" code="newUserRegistration.placeholder.firstName" />
<spring:message var="lastNamePlaceholder" code="newUserRegistration.placeholder.lastName" />
<spring:message var="emailPlaceholder" code="newUserRegistration.placeholder.email" />
<spring:message var="passwordPlaceholder" code="newUserRegistration.placeholder.password" />
<spring:message var="confirmPasswordPlaceholder" code="newUserRegistration.placeholder.confirmPassword" />
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>${pageTitle}</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>	
	<%@ include file="../navbar.jspf" %>
	<div class="well">
		<h1>${pageTitle}</h1>
		<div class="alert alert-info">${msgAllFieldsRequired}</div>
		<form:form action="." modelAttribute="account">
			<div class="form-group">
				<label><spring:message code="newUserRegistration.label.username" /></label>
				<form:input path="username" class="form-control" placeholder="${usernamePlaceholder}"/> 
			</div>
			<div class="form-group">
				<label><spring:message code="newUserRegistration.label.password" /></label>
				<form:password path="password" class="form-control" placeholder="${passwordPlaceholder}"/> 
			</div>
			<div class="form-group">
				<label><spring:message code="newUserRegistration.label.confirmPassword" /></label>
				<form:password path="confirmPassword" class="form-control" placeholder="${confirmPasswordPlaceholder}" /> 
			</div>
			<div class="form-group">
				<label><spring:message code="newUserRegistration.label.firstName" /></label>
				<form:input path="firstName" class="form-control" placeholder="${firstNamePlaceholder}"/> 
			</div>
			<div class="form-group">
				<label><spring:message code="newUserRegistration.label.lastName" /></label>
				<form:input path="lastName" class="form-control" placeholder="${lastNamePlaceholder}" /> 
			</div>
			<div class="form-group">
				<label><spring:message code="newUserRegistration.label.email" /></label>
				<form:input path="email" class="form-control" placeholder="${emailPlaceholder}" /> 
			</div>
			<div class="checkbox">
				<label>
					<form:checkbox path="marketingOk" /> <spring:message code="newUserRegistration.label.marketingOk" />
				</label> 
			</div>
			<div class="checkbox">
				<label>
					<form:checkbox path="acceptTerms" /> <spring:message code="newUserRegistration.label.acceptTerms" />
				</label> 
			</div>			
			<input type="submit" value="${register}" class="btn btn-default" />
		</form:form>
	</div>	
</body>
</html>