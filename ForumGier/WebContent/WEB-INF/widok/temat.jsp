<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum - temat ${temat.tytul}</title>
</head>
<body>
	<h1>Temat: ${temat.tytul}</h1>
	<table border="1">
		<tr>
			<th>Login</th>
			<th width="500">Treść</th>
			<th>Data</th>
		</tr>
		<tr>
			<td>${temat.uzytkownik.login}</td>
			<td>${temat.tresc}</td>
			<td>${temat.data}</td>
		</tr>
		<c:forEach var="wpis" items="${temat.wpisy}">
			<td>${wpis.uzytkownik.login}</td>
			<td>${wpis.tresc}</td>
			<td>${wpis.data}</td>
		</c:forEach>
	</table>
	<form method="post">
		<input type="hidden" name="id" value="${temat.id}" />
		<textarea name="tresc" cols="40" rows = "8"></textarea><br/>
		<input type="submit" value="OK">
	</form>
</body>
</html>