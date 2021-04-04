<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dto.ex.BoardDTO" %>
<%@ page import="java.util.ArrayList" %>
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
<% 
	String bid=(String)request.getParameter("bid");
	request.setAttribute("bid", bid);
%>
<br><br><br>
<form action="modify.do?bid=${bid }" method="post">
	<table>
		<tr>
			<td colspan="2">글 수정 페이지입니다.</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td id="input"><input type="text" name="btitle" size="50"></td>
		</tr>
	
		<tr>
			<td>글내용</td>
			<td id="input"><textarea name="bcontent" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button type="submit">글수정</button></td>
		</tr>
	</table>
</form>
</body>
</html>