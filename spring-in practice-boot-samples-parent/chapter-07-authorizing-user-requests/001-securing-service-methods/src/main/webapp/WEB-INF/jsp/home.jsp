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
            <p>
                This is the home page.<br>
                The contents of this page are not protected.
            </p>
            <div class="well well-lg">
                <ul>    
                    <li>
                        Log in as Elvira (user). Try to block a message of a forum she moderates, you should'nt
                        be able to do it.
                    </li>
                    <li>
                        Juan is an admin. He can block and delete messages. 
                    </li>                    
                </ul>
            </div>
        </div>
	</body>
</html>