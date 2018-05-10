<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forum</title>
</head>
<body>
		<h1>Forum</h1>
		<table>
			<tr>
				<th>Temat</th>
				<th>Data</th>
				<th>Autor</th>
			</tr>
			<c:forEach var="temat" items="${tematy }"> 
				<tr>
					<td>
						<c:url var="url" scope="page" value="/temat">
							<c:param name="id" value="${temat.id}"></c:param>
						</c:url>
						<a href="${url}">${temat.tytul}</a>
					</td>
					<td><fmt:formatDate value="${temat.data}" dataStyle="short" timeStyle="short" type="both"/></td>
					<td>${temat.uzytkownik.login}</td>
				
				</tr>
			</c:forEach>
		</table>
		<c:url value="/nowyTemat" var="urlNowyTemat" scope="page"/>
		<form action="${urlNowyTemat}">
			<input type="submit" value="Nowy temat" />
		</form>
		<br/>
		<a href="wyloguj">Wyloguj</a>
</body>
</html>