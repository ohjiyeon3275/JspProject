<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="write.do" method="post">
	<table>
		<tr>
			<td>글제목</td>
			<td id="input"><input type="text" name="btitle" size="50"></td>
		</tr>
	
		<tr>
			<td>글내용</td>
			<td id="input"><textarea name="bcontent" rows="10"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><button type="submit">글쓰기</button></td>
		</tr>
	</table>
</form>

</body>
</html>