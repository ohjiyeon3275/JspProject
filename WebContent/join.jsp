<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JoinPage</title>
</head>
<body>
<form action="join.se" method="post">
<table style="margin-left:auto; margin-right:auto">
	<tr>
	<td>아이디</td><td><input type="text" name="id"></td>
	</tr>
	<tr>
	<td>패스워드</td><td><input type="password" name="password"></td>
	</tr>
	<tr>
	<td>생년월일</td><td><input type="date" name="birthday"></td>
	</tr>
	<tr>
	<td>이메일</td><td><input type="email" name="email"></td>
	</tr>
	<tr>
	<td>취미</td><td><input type="checkbox" name="hobby" value="workout">운동
	<input type="checkbox" name="hobby" value="music">음악<input type="checkbox" name="hobby" value="movie">영화</td>
	</tr>
	<tr>
	<td><input type="submit" value="회원가입"/><input type="reset" value="다시 입력"/></td>
	</tr>
</table>	
</form>
</body>
</html>