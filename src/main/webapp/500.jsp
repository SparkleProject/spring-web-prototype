<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <title>System error</title>
<style type="text/css">
.error{
font-size: 14px;
color: #444;
}
</style>
</head>

<body>
<div class="error">
       <p>
	 request URI： <%=request.getAttribute("javax.servlet.error.request_uri")%> <br>
           status code ： <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
<%-- 	 exception type： <%=request.getAttribute("javax.servlet.error.exception_type")%> <br>
	 exception message： <%=request.getAttribute("javax.servlet.error.message")%> <br> --%>
	 exception： <%=request.getAttribute("javax.servlet.error.exception")%> <br>
       </p>

</div>
</body>

</html>

