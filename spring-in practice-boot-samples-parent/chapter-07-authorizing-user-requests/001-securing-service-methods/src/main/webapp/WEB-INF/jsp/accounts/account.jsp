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
        <title>SiP - Account: <c:out value="${account.fullName}" /></title>
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
                                
                <div class="container">
                    <h1><c:out value="${account.fullName}"/></h1>
                    <div class="alert alert-warning">
                        Security related fields are not normally shown in a user profile.<br>
                        They're displayed here for informational purposes in this test application.
                    </div>
                    
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="col-sm-2">
                                Username
                            </div>
                            <div class="col-sm-10">
                                <c:out value="${account.username}" />
                            </div>
                            <div class="col-sm-2">
                                Email
                            </div>
                            <div class="col-sm-10">
                            <span class="glyphicon glyphicon-envelope"></span>
                                <a href="mailto:${account.email}"><c:out value="${account.email}" /></a>
                            </div>                            
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="col-sm-2">
                                Account enabled
                            </div>
                            <div class="col-sm-10">
                                <c:out value="${account.enabled}" />
                            </div>
                            
                            <div class="col-sm-2">
                                Roles
                            </div>
                            <c:forEach var="role" items="${account.roles}" varStatus="index">
                                <c:choose>
                                    <c:when test="${index.first}">
                                        <div class="col-sm-10">
                                            <c:out value="${role.name}" />
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:out value="${role.name}" />
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            
                            <div class="col-sm-2">
                                Permissions
                            </div>
                            <c:forEach var="permission" items="${account.permissions}" varStatus="index">
                                <c:choose>
                                    <c:when test="${index.first}">
                                        <div class="col-sm-10">
                                            <c:out value="${permission.name}" />
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:out value="${permission.name}" />
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                                                                                    
                        </div>
                    </div>
                </div>                
            </div>
        </div>
	</body>
</html>