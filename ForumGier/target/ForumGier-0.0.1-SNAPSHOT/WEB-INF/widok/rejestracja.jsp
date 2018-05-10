<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum - rejestracja</title>
</head>
<body>
		<h1>Rejestracja</h1>
		${blad}
		<form method="post">
			<p>Login:</p>
			<input type="text" name="login" maxlength="30"/>
			<p>Wprowadź hasło:</p>
			<input type="password" name="haslo" maxlength="20"/>
			<p>Potwierdź hasło:</p>
			<input type="password" name="haslo2" maxlength="20"/><br/>
			<input type="submit" value="Rejestruj" />
		</form>
</body>
</html>