<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<button id="writeBtn">게시글 등록</button>
	</div>
	<div>
		<table class="">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="userPost" items="${list }">
					<tr>
						<td><c:out value="${userPost.up_idx}"></c:out></td>
						<td>
							<a class="moveView" href="${userPost.up_idx }">
							<c:out value="${userPost.title }"></c:out>
							</a>
						</td>
						<td><c:out value="${userPost.u_writer}"></c:out></td>
						<td>
							<fmt:formatDate value="${userPost.reg_date}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 페이징 처리(예정) -->
		<form action="/userPost/main" method="get" id="actionForm">
		</form>
	</div>
	
	<script type="text/javascript">
	var actionForm = $("#actionForm");

	$(function() {
		var writeBtn = document.querySelector("#writeBtn");
		writeBtn.onclick = function() {
			actionForm.attr('action', '/userPost/write');
			actionForm.submit();
		}
	});

	$('.moveView').on('click', function(e) {
		e.preventDefault();
		
		actionForm.attr('action', '/userPost/view');
		actionForm.append("<input type='hidden' name='up_idx' value='" + $(this).attr('href') + "'/>");
		
		actionForm.submit();
	});
</script>
	
</body>
</html>