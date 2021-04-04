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
#bid, #id, #bdate, #bhit{
	width:auto;
}
#btitle{
	width:200px;
}
</style>

</head>
<body>
<div>
	<table>
		<tr>
		<% 
			String id = (String)session.getAttribute("id");
			if(id!=null){
		%>
			<td colspan="5" align="right"><a href="write.jsp">글 작성하기</a></td>
		<%
			}
		%>
		</tr>
		<tr>
			<td id="bid">번호</td>
			<td id="id">아이디</td>
			<td id="btitle">제목</td>
			<td id="bdate">날짜</td>
			<td id="bhit">조회수</td>
		</tr>


		<c:forEach var="list" items="${list}">
		<tr>
			<td>${list.bid }</td>
			<td>${list.bname }</td>
			<td>
			<c:forEach begin="1" end="${list.bindent }">&nbsp;&nbsp;</c:forEach>	
			<c:if test="${list.bindent!=0 }">ㄴ</c:if>
				<a href="boardView.do?bid=${list.bid }">${list.btitle }</a>
			</td>
			<td>${list.bdate }</td>
			<td>${list.bhit }</td>
		</tr>
		</c:forEach>

	</table>
</div>
</body>
</html>