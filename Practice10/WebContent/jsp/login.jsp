<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form name="loginForm" method="post" action="controller">
	
		<input type="hidden" name="command" value="login" />
		
		Login:<br/>
		<input type="text" name="login" value=""/>
		
		<br/>Password<br/>
		<input type="password" name="password" value=""/>
		
		<br/>
		<input type="submit" value="Log in"/>
		
		<br/>
		${errorLoginPassMessage}
		<br/>
		${wrongAction}
		<br/>
		${nullPage}
		
	</form>
</body>
</html>