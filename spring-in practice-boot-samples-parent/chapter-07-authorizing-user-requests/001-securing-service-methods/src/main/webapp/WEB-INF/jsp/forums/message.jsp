<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="homeUrl" value="/home" />
<c:url var="forumsUrl" value="/forums" />

<!doctype html>
<html>
	<head>
        <meta charset="utf-8">
        <title>SiP - Forum: <c:out value="${message.forum.name}" />, Message: ${message.subject}</title>
        <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">   
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
                                
                <div class="well well-sm">
                    <h3>
                        <c:out value="${message.subject}"/>
                        <span class="pull-right">
                            <small>
                                <span class="glyphicon glyphicon-user"></span>
                                &nbsp;Forum moderated by <a href="#"><c:out value="${message.forum.owner.fullName}"/></a>
                            </small>
                        </span>
                    </h3>
                </div>
                       
                <c:if test="${not message.visible} == true">
                    <div class="alert alert-warning">
                        This message has been blocked. Only administrators and forum moderators can see it.
                    </div>                
                </c:if>         
                
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Posted by <span class="glyphicon glyphicon-user"></span>
                        <a href="#"><c:out value="${message.author.fullName}"/></a>
                        on <span class="glyphicon glyphicon-calendar"></span>&nbsp;
                        <fmt:formatDate type="both" timeStyle="short" value="${message.dateCreated}"/>
                    </div>
                    <div class="panel-body">
                        <c:out value="${message.text}" escapeXml="false"/>
                    </div>                    
                </div>
                
                <div class="btn-group btn-group-justified">
                    <a class="btn btn-primary" href="#" title="Edit message subject, text or visibility"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edit Message</a>
                    <a class="btn btn-warning" href="#" title="Hide messages from users without deleting it"><span class="glyphicon glyphicon-eye-close"></span>&nbsp;Block Message</a>
                    <a class="btn btn-success" href="#" title="Allow users to see this message again"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;Unblock Message</a>
                    <a class="btn btn-danger" href="#" title="Permanently delete this message"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;Delete Message</a>
                </div>
            </div>
        </div>
	</body>
</html>