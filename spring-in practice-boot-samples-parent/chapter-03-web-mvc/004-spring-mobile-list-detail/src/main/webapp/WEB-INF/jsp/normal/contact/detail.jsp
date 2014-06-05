<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Contact Info</title>
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap-theme.css">	
</head>
<body>
    <%@ include file="../navbar.jspf" %>
    <div class="well">	
	    <h1>Contact Info (Normal)</h1>
	    <b>${contact}</b><br/>
	    <table>
	       <tr>
	           <td><b>Phone</b></td>
	           <td>${contact.phone}</td>
	       </tr>
           <tr>
               <td><b>email</b></td>
               <td>${contact.email}</td>
           </tr>	       
	    </table>
    </div>
    
    <p><a href="list.do">All Contacts</a>
</body>
</html>