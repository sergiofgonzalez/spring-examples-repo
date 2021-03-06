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
                                &nbsp;Forum moderated by <a href="/accounts/${message.forum.owner.username}"><c:out value="${message.forum.owner.fullName}"/></a>
                            </small>
                        </span>
                    </h3>
                </div>
                
                <c:if test="${param.cancel == true}">
                    <div class="alert alert-info">
                        <span class="glyphicon glyphicon-ok"></span>
                        &nbsp;Message Deletion has been cancelled.
                    </div>                
                </c:if>   
                    
                <c:if test="${not message.visible}">
                    <div class="alert alert-warning">
                        <span class="glyphicon glyphicon-eye-close"></span>
                        This message has been blocked. Only administrators and forum moderators can see it.
                    </div>                
                </c:if>         
                
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Posted by <span class="glyphicon glyphicon-user"></span>
                        <a href="/accounts/${message.author.username}"><c:out value="${message.author.fullName}"/></a>
                        on <span class="glyphicon glyphicon-calendar"></span>&nbsp;
                        <fmt:formatDate type="both" timeStyle="short" value="${message.dateCreated}"/>
                    </div>
                    <div class="panel-body">
                        <c:out value="${message.text}" escapeXml="false"/>
                    </div>                    
                </div>
                <!--
                    ACL Permissions and their Numeric Values
                    ----------------------------------------
                    |READ   |   1   |
                    |WRITE  |   2   |
                    |CREATE |   4   |
                    |DELETE |   8   |
                    |ADMIN  |   16  |
                    ---------------------------------------- 
                 -->
                <security:accesscontrollist hasPermission="2,8,16" domainObject="${message}">
                    <div class="btn-group btn-group-justified">
                        <c:choose>
                            <c:when test="${!param.confirm}">
                                <security:accesscontrollist hasPermission="2" domainObject="${message}">
                                    <a class="btn btn-primary" href="/forums/${message.forum.id}/messages/${message.id}/edit" title="Edit message subject, text or visibility"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edit Message</a>
                                </security:accesscontrollist>
                                <security:accesscontrollist hasPermission="16" domainObject="${message}">    
                                    <c:choose>                                    
                                        <c:when test="${message.visible}">
                                            <a class="btn btn-warning" href="/forums/${message.forum.id}/messages/${message.id}/visible?block=true" title="Hide messages from users without deleting it"><span class="glyphicon glyphicon-eye-close"></span>&nbsp;Block Message</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-success" href="/forums/${message.forum.id}/messages/${message.id}/visible?block=false" title="Allow users to see this message again"><span class="glyphicon glyphicon-eye-open"></span>&nbsp;Unblock Message</a>
                                        </c:otherwise>                                    
                                    </c:choose>
                                </security:accesscontrollist>
                                
                                <security:accesscontrollist hasPermission="8" domainObject="${message}">                                            
                                    <a class="btn btn-danger" href="/forums/${message.forum.id}/messages/${message.id}/delete/?confirm=false" title="Permanently delete this message"><span class="glyphicon glyphicon-remove-sign"></span>&nbsp;Delete Message</a>
                                </security:accesscontrollist>
                            </c:when>
                            <c:otherwise>
                                <security:accesscontrollist hasPermission="8" domainObject="${message}">
                                    <a class="btn btn-danger" href="/forums/${message.forum.id}/messages/${message.id}/delete/?confirm=true" title="Permanently delete this message"><span class="glyphicon glyphicon-ok"></span>&nbsp;Confirm Message Deletion</a>
                                    <a class="btn btn-default" href="/forums/${message.forum.id}/messages/${message.id}?cancel=true" title="Permanently delete this message"><span class="glyphicon glyphicon-remove"></span>&nbsp;Cancel Message Deletion</a>
                                </security:accesscontrollist>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </security:accesscontrollist>
            </div>
        </div>
	</body>
</html>