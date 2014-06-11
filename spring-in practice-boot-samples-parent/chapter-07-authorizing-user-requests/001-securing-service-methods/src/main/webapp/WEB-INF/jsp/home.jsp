<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
	<head>
        <meta charset="utf-8">
        <title>SiP - Home</title>
        <link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="static/css/bootstrap-theme.css">   
	</head>
	<body>
        <%@ include file="navbar.jspf" %>
            
        <!-- The contents -->
        <div class="well-sm">
            This is the home page.
            The contents of this page are not protected.
        </div>
	</body>
</html>