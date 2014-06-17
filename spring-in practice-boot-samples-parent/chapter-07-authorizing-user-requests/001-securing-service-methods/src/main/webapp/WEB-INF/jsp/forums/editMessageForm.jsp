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
        <title>SiP - Forum: <c:out value="${originalMessage.forum.name}" />, Edit <c:out value="${originalMessage.subject}"></c:out> Message</title>
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
                    <li><a href="${forumsUrl}/${originalMessage.forum.id}">${message.forum.name}</a></li>
                    <li><a href="${forumsUrl}/${originalMessage.forum.id}/messages/${originalMessage.id}">${originalMessage.subject}</a></li>
                    <li><span  class="glyphicon glyphicon-hand-right"></span></li>
                </ol>
                                
                <div class="container">
                    <h3>
                        <spring:message code="editMessage.pageTitle"/>
                    </h3>
                </div>
                
                <c:if test="${param.saved == true}">
                    <div class="alert alert-success">
                        <span class="glyphicon glyphicon-ok"></span>
                        &nbsp;Message saved. <a href="/forums/${originalMessage.forum.id}/messages/${originalMessage.id}">View it.</a>
                    </div>
                </c:if>
                
                <c:if test="${not originalMessage.visible}">
                    <div class="alert alert-warning">
                        <span class="glyphicon glyphicon-eye-close"></span>
                        &nbsp;This message has been blocked. Only administrators and forum moderators can see it.
                    </div>                
                </c:if>
                
                <form:form class="form-horizontal" modelAttribute="message" action="/forums/${originalMessage.forum.id}/messages/${originalMessage.id}" method="PUT">
                    <form:errors path="*">
        				<div class="alert alert-danger">
        					<span class="glyphicon glyphicon-remove">&nbsp;</span><spring:message code="error.global" />
        				</div>
                    </form:errors>
                
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Subject</label>
                        <div class="col-sm-10">
                            <form:input type="text" path="subject" class="form-control" placeholder="Enter Message Subject"/>
                        </div>
                        <form:errors path="subject" htmlEscape="true" cssClass="error" />                        
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Author</label>
                        <div class="col-sm-10">
                            <p class="form-control-static"><span class="glyphicon glyphicon-user"></span>&nbsp;<a href="#"><c:out value="${originalMessage.author.fullName}"/></a></p>
                        </div>                        
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date</label>
                        <div class="col-sm-10">
                            <p class="form-control-static"><span class="glyphicon glyphicon-calendar"></span>&nbsp;<fmt:formatDate type="both" dateStyle="long" value="${originalMessage.dateCreated}"/></p>
                        </div>                        
                    </div>                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Text</label>
                        <div class="col-sm-10">
                            <form:textarea rows="10" path="text" class="form-control" placeholder="Enter Message Text"></form:textarea>
                        </div>
                        <form:errors path="text" htmlEscape="true" cssClass="error" />                        
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" value="Save" class="btn btn-primary" />
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                </form:form>
            </div>
        </div>
	</body>
</html>