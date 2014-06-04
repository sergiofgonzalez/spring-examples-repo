<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Thanks</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>
    <%@ include file="../navbar.jspf" %>
    <div class="well">
	    <h1>Thanks</h1>
        <p>Thanks for nominating ${nominee}</p>
    </div>
    
    <div>
        <a class="navbar-link" href="list.do">Back</a>
    </div>	
</body>
</html>