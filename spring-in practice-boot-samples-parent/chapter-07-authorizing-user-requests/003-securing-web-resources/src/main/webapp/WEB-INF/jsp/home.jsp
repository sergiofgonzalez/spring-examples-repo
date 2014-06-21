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
                        Log in as Daniel (user). He will be able to edit messages but will not be
                        able to block or delete them.
                    </li>    
                    <li>
                        Log in as Paula (student). She won't be able to read her account and neither
                        will be able to post messages.
                    </li>
                    <li>
                        Juan is an admin. Check that once he has logged in, in the navbar the Forums and Admin
                        sections are enabled. Also check that he has enabled the option to post messages.
                    </li>                    
                </ul>
            </div>
        </div>
	</body>
</html>