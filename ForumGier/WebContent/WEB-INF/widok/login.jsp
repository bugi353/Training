<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum logowanie</title>
</head>
<body>
	<h1>Zaloguj się</h1>
	<form method="post" action="j_security_check">
		<p>Login:</p>
		<input type="text" name="j_username"/>
		<p>Hasło:</p>
		<input type="text" name="J_password" /><br/>
		<input type="submit" value="Zaloguj"/>
	</form>
	<a href="rejestruj">Rejestracja</a>
</body>
</html>