<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="newRegistrationUrl" value="/accounts/new" />
<spring:message var="pageTitle" code="registrationOk.pageTitle" />

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
		<div class="alert alert-info">
			<p><spring:message code="registrationOk.message.thanks" /></p>
			<p><a href="${newRegistrationUrl}"><spring:message code="registrationOk.label.continue" /></a>
		</div>		
	</div>	
</body>
</html>