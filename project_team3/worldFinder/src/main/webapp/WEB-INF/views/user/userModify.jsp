<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
	<div>
		<%@include file="../include/logo.jsp"%>
	</div>
	<div>
		<h2>기본 프로필 수정</h2>
		<form id="join_form" method="post" >
			<table>
			
				<tr>
					<th>아이디</th>				
					<td><input type="text" name="u_writer" id="user_id"
						class="form-control tooltipstered" maxlength="14"
						required="required" aria-required="true"
						placeholder="문자, 숫자포함(필수 입력)"> 
						<span id="idCk"></span> </td>
				</tr>
				<tr>
					<th>비밀번호 입력</th>				
					<td><input type="password" name="u_pw" id="password" placeholder="비밀번호 입력(필수입력)"> 
						<span id="pwChk"></span> </td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>				
					<td><input type="password" name="u_pw" placeholder="비밀번호를 한번 더 입력해 주세요." id="password_check"> 
						<span id="pwChk2"></span> </td>
				</tr>
				<tr>
					<th>성명</th>				
					<td><input type="text" name="u_name" id="name" placeholder="이름입력 (필수입력)"> </td>
				</tr>
				<tr>
					<th>생년월일</th>				
					<td><input type="text" name="birth" id="birth" placeholder="생년월일 8자리(필수입력)"> </td>
				</tr>
				<tr>
					<th>연락처</th>				
					<td><input type="text" name="phone" placeholder="010-1234-5678(필수입력)" id="phone"> </td>
				</tr>
				<tr>
					<th>이메일</th>				
					<td><input type="email" name="mail" id="mail" placeholder="(필수입력)"> </td>
				</tr>
				<tr>
					<th>성별</th>				
					<td>
						<input type="radio" name="gender" value="남" id="male" checked="checked">남
						<input type="radio" name="gender" value="여" id="female">여 
					</td>
				</tr>
				<tr>
					<th>국적</th>				
					<td><input type="text" name="nationality" id="naion" placeholder="대한민국(필수입력)"> </td>
				</tr>
			</table>
				<input type="button" value="회원가입" id="signup-btn">
				<input type="reset" value="취소">
		</form>
	</div>
</body>
</html>