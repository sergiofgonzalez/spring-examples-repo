<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Roster: List View</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>
    <div class="well">	
	    <h1>Roster: List View</h1>
	    <div class="panel panel-default">
	        <div class="panel-heading">Roster: List View</div>
			    <ul class="list-group">
			        <c:forEach var="member" items="${memberList}" varStatus="status">
			            <li class="list-group-item">
			                <a href="member.do?id=${status.index}"><c:out value="${member}"/></a>
			            </li>
			        </c:forEach>
			    </ul>
		    </div>
	    </div>
    </div>
</body>
</html>