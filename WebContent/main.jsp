 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
</head>

<body>
<div style="text-align:right;margin-right:100px;">
<br>
<%

String id = (String)session.getAttribute("id");

if(id==null){	
%>
	<a href ="login.jsp">로그인</a>&nbsp;
	<a href ="join.jsp">회원가입</a>
<%
}else{
%>
	<%=id%>님이 로그인 하셨습니다.
<%
}

%>
</div>
<br><br>
<div style="text-align:center">
	<a href="board.do">게시판</a>
</div>

</body>
</html>