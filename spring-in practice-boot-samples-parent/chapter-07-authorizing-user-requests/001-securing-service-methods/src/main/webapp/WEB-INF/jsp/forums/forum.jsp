<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="homeUrl" value="/home" />
<c:url var="forumsUrl" value="/forums" />

<!doctype html>
<html>
	<head>
        <meta charset="utf-8">
        <title>SiP - Forum: <c:out value="${forum.name}" /></title>
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
                    <li><span  class="glyphicon glyphicon-hand-right"></span></li>
                </ol>
                                
                <div class="well well-sm">
                    <h1>
                        <c:out value="${forum.name}"/>
                        <span class="pull-right">
                            <small>
                                <span class="glyphicon glyphicon-user"></span>
                                &nbsp;Forum moderated by <a href="#"><c:out value="${forum.owner.fullName}"/></a>
                            </small>
                        </span>
                    </h1>
                </div>
                
                <c:if test="${param.deleted == true}">
                   <div class="alert alert-warning">
    					Message deleted.
    				</div>
                </c:if>
                
                <c:choose>
                    <c:when test="${empty forum.messages}">
                        <p>Be the first to <a href="#">post a message</a></p>
                    </c:when>
                    <c:otherwise>
                        <div class="well well-sm">
                            Messages <span class="badge active">${forum.numVisibleMessages}</span>  | <a href="/forums/${forum.id}/messages/new"><span class="glyphicon glyphicon-edit"></span>&nbsp;Post a message</a>
                        </div>
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Message</th>
                                    <th>Author</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="message" items="${forum.messages}">
                                    <c:url var="messageUrl" value="/forums/${forum.id}/messages/${message.id}" />
                                    <c:url var="authorUrl" value="#" />
                                    <fmt:formatDate var="date" type="both" timeStyle="short" value="${message.dateCreated}"/>
                                    <tr>
                                        <td>
                                            <a href="${messageUrl}"><c:out value="${message.subject}"/></a>
                                            <c:if test="${not message.visible }">
                                                <b>[BLOCKED]</b>
                                            </c:if>
                                        </td>
                                        <td>
                                            <span class="glyphicon glyphicon-user"></span>
                                            <a href="#"><c:out value="${message.author.fullName}"/></a>
                                        </td>
                                        <td>
                                            <span class="glyphicon glyphicon-calendar"></span>
                                            ${date}
                                        </td>
                                    </tr>                                
                                </c:forEach>
                            </tbody>                        
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
	</body>
</html>