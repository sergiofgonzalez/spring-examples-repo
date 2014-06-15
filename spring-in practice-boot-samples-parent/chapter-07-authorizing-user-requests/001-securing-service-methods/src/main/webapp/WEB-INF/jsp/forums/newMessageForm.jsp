<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="homeUrl" value="/home" />
<c:url var="forumsUrl" value="/forums" />

<!doctype html>
<html>
	<head>
        <meta charset="utf-8">
        <title>SiP - Forum: <c:out value="${message.forum.name}" />, New Message</title>
        <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">
        <style type="text/css">
            span.error {
                color: red;
            }
        </style>   
	</head>
	<body>
        <%@ include file="../navbar.jspf" %>
        <!-- The contents -->
        <div class="well well-sm">
            <div class="container">        
                <ol class="breadcrumb">
                    <li><a href="${homeUrl}">Home</a></li>
                    <li><a href="${forumsUrl}">Forums</a></li>
                    <li><a href="${forumsUrl}/${message.forum.id}">${message.forum.name}</a></li>
                    <li><span  class="glyphicon glyphicon-hand-right"></span></li>
                </ol>
                                
                <div class="container">
                    <h3>
                        <spring:message code="postNewMessage.pageTitle"/>
                    </h3>
                </div>
                <form:form class="form-horizontal" modelAttribute="message" action="/forums/${message.forum.id}/messages/new">
                    <form:errors path="*">
        				<div class="alert alert-danger">
        					<span class="glyphicon glyphicon-remove">&nbsp;</span><spring:message code="error.global" />
        				</div>
                    </form:errors>
                
                    <div class="input-group">
                        <span class="input-group-addon">Subject</span>
                        <form:input type="text" path="subject" class="form-control" placeholder="Enter Message Subject"/>
                    </div>
                    <form:errors path="subject" htmlEscape="true" cssClass="error" />
                    <br>
                    <form:textarea rows="10" path="text" class="form-control" placeholder="Enter Message Text"></form:textarea>
                    <form:errors path="text" htmlEscape="true" cssClass="error" />
                    <br>
                    <div class="input-group">
                        <input type="submit" value="Post Message" class="btn btn-primary" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                </form:form>
            </div>
        </div>
	</body>
</html>