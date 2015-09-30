<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:url var="postLoginUrl" value="/j_spring_security_check" />
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
    	<div class="well">
    		<c:if test="${!param.failed}">
    			<div class="alert alert-warning">			
    				Please Log In						
    			</div>
    		</c:if>
    		<form action="${postLoginUrl}" method="post" class="form-horizontal">
    			<c:if test="${param.failed == true}">
    				<div class="alert alert-danger">
    					Your login attempt failed. Please try again, or contact technical support
    					for further assistance.
    				</div>
    			</c:if>
                <div class="container">
    			<div class="form-group">
    				<label class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-10">
    				    <input type="text" name="j_username" class="form-control" placeholder="your username"/>
                    </div>
    			</div>
    			<div class="form-group">
    				<label class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
    				    <input type="password" name="j_password" class="form-control" placeholder="your password"/>
                    </div>
    			</div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
            			<div class="checkbox">
            				<label>
            					<input type="checkbox" name="_spring_security_remember_me" />
            					Remember Me
            				</label>
            			</div>
                    </div>
                </div>
                <div class="form-group">
        			<div class="col-sm-offset-2 col-sm-10">
        				<input type="submit" value="Log In" class="btn btn-primary" />
        				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        			</div>
                </div>
                </div>
    		</form>		
    	</div>
	</body>
</html>