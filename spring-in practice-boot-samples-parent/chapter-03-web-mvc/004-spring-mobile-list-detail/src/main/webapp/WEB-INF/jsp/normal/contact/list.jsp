<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>All Contacts</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>
    <%@ include file="../navbar.jspf" %>
    <div class="well">	
	    <h1>All Contacts (Normal)</h1>
        <ul>
            <c:set var="currentLetter" value=""/>
            <c:forEach var="contact" items="${contactList}" varStatus="status">
                <c:set var="lastNameInitial" value="${fn:toUpperCase(fn:substring(contact.lastName, 0, 1))}" />
                <c:if test="${currentLetter != lastNameInitial}">
                    <c:set var="currentLetter" value="${lastNameInitial}"/>
                    <li><b><c:out value="${currentLetter}"/></b></li>
                </c:if>
                <li><a href="detail.do?id=${status.index}"><c:out value="${contact.firstName} ${contact.lastName}"/></a></li>
            </c:forEach>
        </ul>
    </div>
    
    <c:if test="${currentDevice.mobile}">
        <c:choose>
            <c:when test="${currentSitePreference.mobile}">
                <a href="${currentUrl}?site_preference=normal">Switch To: Normal Site</a>
            </c:when>
            <c:otherwise>
                <a href="${currentUrl}?site_preference=mobile">Switch To: Mobile Site</a>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>