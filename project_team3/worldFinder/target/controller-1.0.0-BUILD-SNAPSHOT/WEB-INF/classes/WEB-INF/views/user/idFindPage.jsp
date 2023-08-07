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

<body>
	<div>
		<%@include file="../include/logo.jsp"%>
	</div>
		<h2>아이디 찾기</h2>
		<form method="post" class="idFind" name='findform'>
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
					<th> <input class="btn btn-lg btn-secondary btn-block text-uppercase" type="submit" value="아이디찾기" > </th>
				</tr>
				
				
				<!-- 이름과 전화번호가 일치하지 않을 때-->
		<c:if test="${check == 1}">
			<script>
				opener.document.findform.name.value = "";
				opener.document.findform.phone.value = "";
			</script>
			<label>일치하는 정보가 존재하지 않습니다.</label>
		</c:if>

		<!-- 이름과 비밀번호가 일치하지 않을 때 -->
		<c:if test="${check == 0 }">
		<label>찾으시는 아이디는' ${id}' 입니다.</label>
		<div class="form_group">
				<input class="btn btn-lg btn-secondary btn-block text-uppercase"
					type="submit" value="OK" onclick="closethewindow()">
			</div>
		</c:if>
				
			</table>
		</form>
</body>

<script type="text/javascript">
/*
$(document).ready(function() {
	$("#findId").click(function() {
		alert(1);
		
		var name = $("#name").val();
		var phone = $("#phone").val();
		var email = $("#mail").val();
		if (name == "") {
			alert("아이디를 입력하세요.");
			$("#name").focus();
			return;
		}
		if (phone == "") {
			alert("비밀번호를 입력하세요.");
			$("#phone").focus
			return;
		}
		if (email == "") {
			alert("비밀번호를 입력하세요.");
			$("#mail").focus
			return;
		}
		//$('.idFind').attr("action", "/user/idFind");
		//$(".idFind").submit();
		document.form.action = "${path}/user/idFind"
		document.form.submit();
	});
});

*/
</script>

<script type="text/javascript">
		function closethewindow(){
			$('.idFind').attr("action", "/user/idResult");
			$(".idFind").submit();
		}
</script>

</html>