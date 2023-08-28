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
	<%@ include file="../include/itemFilter.jsp"%>
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
					<th>조회수</th>
					<th>좋아요</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty list }">
					<c:forEach var="userPost" items="${list }">
						<tr>
							<td><c:out value="${userPost.up_idx}"></c:out></td>
							<td>
								<a class="moveView" href="${userPost.up_idx }">
								<c:out value="${userPost.title }"></c:out>
								</a>
							</td>
							<td><c:out value="${userPost.u_writer}"></c:out></td>
							<td><c:out value="${userPost.hit }"></c:out></td>
							<td><c:out value="${userPost.up_like }"></c:out></td>
							<td>
								<fmt:formatDate value="${userPost.reg_date}" pattern="yyyy-MM-dd" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty list }">
					<td colspan="4">게시글이 존재하지 않습니다.</td>
				</c:if>
			</tbody>
		</table>

		<div class="page">
			<ul class="pagination">
				<c:if test="${pageMaker.prev }">
					<li class="paginate_button previous">
						<a href="${pageMaker.startPage-1 }">&lt;</a>
					</li>
				</c:if>
				<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1">
					<li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : '' }">
						<a href="${num }">${num }</a>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<li class="paginate_button">
						<a href="${pageMaker.endPage+1 }">&gt;</a>
					</li>
				</c:if>
			</ul>
		</div>

		<!-- 페이징 처리 -->
		<form action="/userPost/main" method="get" id="actionForm">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
			<input type="hidden" name="country" value="${country }">
		</form>
		
		
	</div>
	
	<script type="text/javascript">
		var actionForm = $("#actionForm");
		var country = '${country}';
	
		$(function() {
			var writeBtn = document.querySelector("#writeBtn");
			writeBtn.onclick = function() {
				actionForm.attr('action', '/userPost/write');
				actionForm.append("<input type='hidden' name='country' value='" + country + "'/>");
				actionForm.submit();
			}
		});
	
		$('.moveView').on('click', function(e) {
			e.preventDefault();
			
			actionForm.attr('action', '/userPost/view');
			actionForm.append("<input type='hidden' name='up_idx' value='" + $(this).attr('href') + "'/>");
			
			actionForm.submit();
		});
		
		/* 페이징 */
		$(".paginate_button a").on('click', function(e) {
			e.preventDefault();
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
	</script>
	<script type="text/javascript">
		function ranName(a) {
			 location.href='/userPost/main?country=' + a;
		}
	</script>
</body>
</html>