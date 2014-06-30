<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
	<head>
        <meta charset="utf-8">
        <title>SiP - Error</title>
        <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">   
        <style type="text/css">
            @font-face {
              font-family: "Pixelated";
              src: url("/static/fonts/LCD_Solid.woff"),
                   url("/static/fonts/LCD_Solid.ttf") ;
            }
            
            h1#errTitle {
                font-family: "Pixelated", sans-serif;
            }
            
            #errPanel {
                border: 1px solid black;
                background-color: white;
            }
            
            #errDesc {
                font-family: "Pixelated", sans-serif;
            }
            
        </style>
	</head>
	<body>
        <%@ include file="navbar.jspf" %>
        
        <!-- The contents -->
        <div class="container">
            <div id="errPanel" class="jumbotron">
                <div class="container">
                    <h1 id="errTitle"><img src="/static/images/bomb.png" alt="mac os bomb icon">
                    Sorry, an error occurred.</h1>
                    <p id="errDesc">
                        <c:if test="${not empty error}">${error}</c:if>
                        <c:if test="${not empty message}">: ${message}</c:if>
                    </p>            
                    <p><a href="/home" class="btn btn-primary btn-lg pull-right"><span class="glyphicon glyphicon-home"></span>&nbsp;Take me home</a></p>
                </div>
            </div>
            <div class="well well-sm">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        Detailed Error Information
                    </div>
                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <th>Error Field</th>
                                <th>Contents</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty timestamp}">
                                <tr>
                                    <td>Timestamp</td>
                                    <td><fmt:formatDate type="both" dateStyle="long" value="${timestamp}"/></td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty status}">
                                <tr>
                                    <td>Status</td>
                                    <td>${status}</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty path}">
                                <tr>
                                    <td>Path</td>
                                    <td>${path}</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty method}">
                                <tr>
                                    <td>Method</td>
                                    <td>${method}</td>
                                </tr>
                            </c:if>                            
                            <c:if test="${not empty error}">
                                <tr>
                                    <td>Error</td>
                                    <td>${error}</td>
                                </tr>
                            </c:if>
                            <c:if test="${not empty message}">
                                <tr>
                                    <td>Message</td>
                                    <td>${message}</td>
                                </tr>
                            </c:if>                        
                        </tbody>
                    </table>                    
                </div>
            </div>            
        </div>
        
        <!--
        Technical Information is included as comments within the page
       
        <c:if test="${not empty exception}">
            Exception: <c:out value="${exception}"/>
            Exception Message: <c:out value="${exception.message}" />
            Exception StackTrace:
            <c:forEach var="stackTraceElem" items="${exception.stackTrace}"><c:out value="${stackTraceElem}"/>
            </c:forEach>
        </c:if>
        
        <c:if test="${not empty errors}">Errors:
            <c:forEach var="error" items="${errors}"><c:out value="${error}"/>
            </c:forEach>
        </c:if>
        <c:if test="${not empty trace}">Trace:
            <c:forEach var="traceElem" items="${trace}"><c:out value="${traceElem}"/>
            </c:forEach>
        </c:if>                
         -->           
	</body>
</html>