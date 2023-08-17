<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="../../../resources/css/base.css">
	<style>
		#body{
			text-align: center;
		}
		table {
			text-align: center;
			margin: auto;
		}
	</style>
</head>
<body>
	<div id="body">
		<div>
			<%@include file="../include/logo.jsp"%>
		</div>
		<h2>비밀번호 찾기</h2>
		<form method="get" id='inform' action="/user/pwFind">
			<table>
				<tr>
					<td><input type="text" name="u_writer" placeholder="아이디 입력"></td>
				</tr>
				<tr>
					<td><input type="text" name="phone" placeholder="연락처 입력"><br> 
						<input type="email" name="mail" placeholder="이메일 입력"> </td>
				</tr> 
				

			</table>
		</form>
		<input type="button" id="fBtn" value="비밀번호 찾기">
	</div>

	<script type="text/javascript">
		const inform = document.getElementById("inform");

		document.getElementById("fBtn").onclick = function() {

			if (inform.u_writer.value == "") {
				alert("아이디를 입력하세요.");
				inform.u_writer.focus();
				return;
			}
			if (inform.phone.value == "") {
				alert("전화번호를 입력하세요.");
				inform.phone.focus();
				return;
			}
			if (inform.mail.value == "") {
				alert("이메일을 입력하세요.");
				inform.mail.focus();
				return;
			}

			let formData = new FormData(inform);
			// 장동완 : 너무 ajax만 남발하는거 같아서
			// fetch 사용에 익숙해지려고 사용
			// 프로미스는 어느정도 숙지한 상태 23/08/10
			fetch("/user/pwFindPage", {
				method: "post",
				headers: {},
				body: formData,
			}).then((response) => response.json())
					.then(data => {
						if (data.result){
							inform.submit();
						} else {
							alert("일치하는 데이터를 찾지 못했습니다.")
							inform.reset();
							inform.u_writer.focus();
						}


					}) .catch((error)=> console.log(error));
		};
	</script>
</body>
</html>