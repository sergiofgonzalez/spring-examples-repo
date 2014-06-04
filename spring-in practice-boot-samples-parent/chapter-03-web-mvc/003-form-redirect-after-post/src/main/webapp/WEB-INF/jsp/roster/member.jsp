<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Roster: Details View</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>
    <%@ include file="../navbar.jspf" %>
    <div class="well">
	    <h1>Member: Detail View</h1>
	    <div class="panel panel-default">
	       <div class="panel-heading">Member</div>
	       <div class="panel-content"><c:out value="${member}" /></div>
	    </div>
	    
	    <div>
	        <a class="navbar-link" href="list.do">Back</a>
	    </div>    
    </div>	
</body>
</html>