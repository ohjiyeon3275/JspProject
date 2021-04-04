<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, tr, td{	
		border:1px black solid;
		margin-left:auto;margin-right:auto;
	}
	#bid,#bdate,#btitle,#bcontent{
		width:400px;
	}
	
	#bcontent{
		height:300px;
	}
	
</style>
</head>
<body>
<%
	String user = (String)session.getAttribute("user");
	String bname = (String)request.getAttribute("bname");
%>
<br><br>
<c:forEach items="${list }" var="view">
	<table>
		<tr>
			<td>작성자</td>
			<td id="id">${view.bname }</td>
		</tr>
		<tr>
			<td>글번호</td>
			<td id="bid">${view.bid }</td>
		<tr>
			<td>작성일자</td>
			<td id="bdate">${view.bdate }</td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td id="btitle">${view.btitle }</td>
		</tr>
		<tr>
			<td>글 내용</td>
			<td id="bcontent">${view.bcontent }</td>
		</tr>
	
		<tr>
		<td colspan="2" style="height:40px;" align="right">		

<%
	
	if(user.equals(bname)){
%>
		<input type="button" onclick="location.href='modify.jsp?bid=${view.bid}'" value="수정">
		<input type="button" onclick="location.href='delete.do?bid=${view.bid}'" value="삭제">
<%

	}
	
	if(user!=null) {
%>	
			<input type="button" onclick="location.href='reply.jsp?bid=${view.bid}'" value="답글">
<%
	} 
%>

			<input type="button" onclick="location.href='board.do'" value="목록">
		</td>
		</tr>
		
	</table>
	</c:forEach>

</body>
</html>