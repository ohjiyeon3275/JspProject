<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dto.ex.BoardDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, tr, td{
		margin-left:auto; margin-right:auto; border:1px solid black;
	}
	textarea{
		width:400px;height:300px;
	}
</style>
</head>
<body>

<br><br><br>

<form action="reply.do?bid=${param.bid }" method="post">

	<table>
		<tr>
			<td>원글 번호</td>
			<td>${param.bid }</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td id="input"><input type="text" name="rtitle" size="50"></td>
		</tr>
	
		<tr>
			<td>글내용</td>
			<td id="input"><textarea name="rcontent" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button type="submit">답글쓰기</button></td>
		</tr>
	</table>

</form>
</body>
</html>