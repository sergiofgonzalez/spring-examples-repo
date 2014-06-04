<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Nominate a Member for the award</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>
    <%@ include file="../navbar.jspf" %>
    <div class="well">	
	    <h1>Nominate a member for the Award</h1>
        <form:form modelAttribute="nominee">
            <div class="form-group">
                <label for="firstName">First Name</label>
                <form:input id="firstName" path="firstName" class="form-control" placeholder="Enter First Name" />
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <form:input id="lastName" path="lastName" class="form-control" placeholder="Enter Last Name" />
            </div>

            <input type="submit" value="Submit" class="btn btn-default"/>
        </form:form>	    
    </div>
</body>
</html>