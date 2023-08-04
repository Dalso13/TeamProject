<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<img alt="." src="/resources/image/logo.png" width="500px" height="150px">
	</div>
	<div>
		<h2>Login</h2>
		<form method="get">
			<table>
				<tr>
					<td><input type="text" name="u_writer" placeholder="아이디"></td>
				</tr>
				<tr>
					<td><input type="password" name="u_pw" placeholder="비밀번호"></td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="remember_check">아이디 저장하기
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인"> 
						<input type="button" value="회원가입"> 
					</td>
				</tr>
				
			</table>		
		</form>
	</div>
</body>
</html>