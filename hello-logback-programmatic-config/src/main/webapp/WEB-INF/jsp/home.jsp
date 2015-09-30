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
                        Log in as Paula (student). She will be able to log in, but won't be able
                        to access any of the forums, as they are configured as READ by users, not
                        students.
                    </li>
                    <li>
                        Log in as Daniel (user). He will be able to see all the forums and 
                        edit messages his own messages,
                        but will not be able to successfully edit other user's messages.
                    </li>    
                    <li>
                        Log in as Julia (moderator). She will be able to see all the forums and 
                        edit messages from other users in the forums she moderate (e.g. Algebra I),
                        but not edit messages from forums other people moderate (e.g. Calculus II).                        
                    </li>                    
                    <li>
                        Juan is an admin. He can edit all messages.
                    </li>                    
                </ul>
            </div>
        </div>
	</body>
</html>