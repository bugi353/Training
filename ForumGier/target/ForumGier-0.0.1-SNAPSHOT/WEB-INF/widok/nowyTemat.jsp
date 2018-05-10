<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum - dodaj nowy temat</title>
</head>
<body>
	<form method="post">
		<p>Wprowadź tytuł tematu:</p>
		<input type="text" name="tytul" maxlength="255" />
		<p>Wprowadź treść tematu:</p>
		<textarea name="tresc" rows="9" cols="40"></textarea><br/>
		<input type="submit" value="Dodaj" />
	</form>
</body>
</html>