<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="../../../resources/css/base.css">
<style>
	#body div{
		text-align: center;
	}
	table {
		text-align: center;
		margin: auto;
	}
</style>
<body>
	<div>
		<%@include file="../include/logo.jsp"%>
	</div>
	<div id="body">
		<h2>아이디 찾기</h2>
		<form method="post" class="idFind" id='inform'>
			<table>
				<tr class="form_group">
					<th>성명</th>				
					<td><input type="text" name="u_name" id="name" placeholder="이름입력 (필수입력)"> </td>
				</tr>
				<tr class="form_group">
					<th>연락처</th>				
					<td><input type="text" name="phone" placeholder="010-1234-5678(필수입력)" id="phone"> </td>
				</tr>
				<tr class="form_group">
					<th>이메일</th>				
					<td><input type="email" name="mail" id="mail" placeholder="(필수입력)"> </td>
				</tr>
				<tr class="form-label-group">
					<th> <input class="btn btn-lg btn-secondary btn-block text-uppercase" id="fBtn" type="button" value="아이디찾기" > </th>
				</tr>
				
				
				<!-- 이름과 전화번호가 일치하지 않을 때-->
<%--		<c:if test="${check == 1}">--%>
<%--			<script>--%>
<%--				opener.document.findform.name.value = "";--%>
<%--				opener.document.findform.phone.value = "";--%>
<%--			</script>--%>
<%--			<label>일치하는 정보가 존재하지 않습니다.</label>--%>
<%--		</c:if>--%>

<%--		<!-- 이름과 비밀번호가 일치하지 않을 때 -->--%>
<%--		<c:if test="${check == 0 }">--%>
<%--		<label>찾으시는 아이디는' ${id}' 입니다.</label>--%>
<%--		<div class="form_group">--%>
<%--				<input class="btn btn-lg btn-secondary btn-block text-uppercase"--%>
<%--					type="submit" value="OK" onclick="closethewindow()">--%>
<%--			</div>--%>
<%--		</c:if>--%>
				
			</table>
		</form>

		<br>
		<hr>
		<br>
		<div id="resultId">
			<h3></h3>
			<div></div>
		</div>
	</div>
	<script type="text/javascript">
		const inform = document.getElementById("inform");

		document.getElementById("fBtn").onclick = function() {

			if (inform.u_name.value == "") {
				alert("아이디를 입력하세요.");
				inform.u_name.focus();
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
			fetch("/user/idFind", {
				method: "post",
				headers: {},
				body: formData,
			}).then((response) => response.text())
					.then(data => {
						if (data == ""){
							$('#resultId h3').html(inform.u_name.value + "님의 아이디가 존재하지 않습니다");
						} else {
							$('#resultId h3').html(inform.u_name.value + "님의 아이디");
							$('#resultId div').html(data);
						}
					})
					.catch((error)=> console.log(error));
		};
	</script>
</body>



</html>