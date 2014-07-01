<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url var="homeUrl" value="/home" />
<!doctype html>
<html>
	<head>
        <meta charset="utf-8">
        <title>SiP - Forums</title>
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
                    <li><span  class="glyphicon glyphicon-hand-right"></span></li>
                </ol>
                
                <h1>Forums</h1>
                <c:choose>
                    <c:when test="${empty forums}">
                        <p>No forums.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Forum</th>
                                    <th class="text-right">#&nbsp;posts</th>
                                    <th>Last post</th>
                                    <th>Moderator</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="forum" items="${forums}">
                                    <c:url var="forumsUrl" value= "/forums/${forum.id}" />
                                    <c:url var="ownerUrl" value= "/accounts/${forum.owner.username}" />                               
                                    
                                    <fmt:formatDate var="date" type="both" timeStyle="short" value="${forum.lastVisibleMessageDate}"/>
                                    
                                    <tr>
                                        <td><a href="${forumsUrl}"><c:out value="${forum.name}"/></a></td>
                                        <td class="text-right">${forum.numVisibleMessages}</td>
                                        <td>
                                            <c:if test="${not empty forum.lastVisibleMessageDate}">
                                                <span class="glyphicon glyphicon-calendar"></span>&nbsp;${date}
                                            </c:if>
                                        </td>
                                        <td><span class="glyphicon glyphicon-user"></span><a href="${ownerUrl}">&nbsp;<c:out value="${forum.owner.fullName}"/></a></td>
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