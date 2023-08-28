<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		
		var editor = document.querySelector('#editor');
		var btnImg = document.querySelector('#btn-img');
		var imgSelector = document.querySelector('#img-selector');

		function focusEditor() {
			editor.focus({preventScroll: true});
		}

		btnImg.addEventListener('click', function() {
			imgSelector.click();
		});

		imgSelector.addEventListener('change', function(e) {
			var files = e.target.files;
			if (files && files.length > 0) {
				var file = files[0];
				if (file.type.startsWith('image/')) {	// 이미지인지 판별
					insertImg(file);
				} else {
					alert('이미지 파일을 선택해주세요.');
				}
			}
		});

		function insertImg(file) {	// 이미지 삽입 함수
			var reader = new FileReader();
			reader.addEventListener('load', function(e) {
				focusEditor();
				document.execCommand('insertImage', false, reader.result);
			});
			reader.readAsDataURL(file);
		}
		
		// 게시글 등록 버튼 클릭 시 div 값 textarea에 값 옮겨주고
		// textarea에 있는 값을 DB에 넣어줌
		// div > textarea > DB
		$('#post-btn').click(function() {	
			$('#append').val($('#editor').html());
		});
		
		
		/* var urlParams = new URLSearchParams(window.location.search);
        var countryValue = urlParams.get('country');
        
        var countryInput = document.querySelector('input[name="country"]');
        countryInput.value = countryValue; */
		
	});
</script>
<!-- 페이지 이동 -->
<script type="text/javascript">
	$(function() {
				
		var operForm = $("#operForm");
		var pageNumTag = $("input[name='pageNum']").clone();
		var amountTag = $("input[name='amount']").clone();
		
		$("#mainBtn").on('click', function() {
			operForm.empty();
			operForm.attr('action', 'main').attr('method', 'get');
			
			operForm.append(pageNumTag);
			operForm.append(amountTag);
			
			operForm.submit();
		});
		
		/* $("#resetBtn").on('click', function() {
			$('form').each(function() {
				this.reset();
			});
		}); */
	});
</script>
<style type="text/css">
	div#editor {
		border: 1px solid #D6D6D6;
		border-radius: 4px;
	}

	#editor img {
		max-width: 40%;
	}
</style>
</head>
<body>
	<button id="mainBtn">목록으로 이동</button>
	<form action="/userPost/write" method="post" id="operForm">
		<input type="text" placeholder="제목을 입력하세요." name="title">
		<input name="u_writer" value='<sec:authentication property="principal.username"/>' readonly="readonly">
		<hr>
		<button type="button" id="btn-img">IMG<!-- <img src="/resources/image/img-icon.png"/> --></button>
		<br><br>
		<div id="editor" contenteditable="true"></div>
		<input id="img-selector" type="file" style="display: none;">
		<br>
		<input type="submit" id="post-btn" value="게시글 등록">

		<input type="hidden" name="${_csrf.parameterName }" value="${_csfr.token }">
		<input type="hidden" name="country" value="${param.country }">
		<input type="hidden" name="pageNum" value="${cri.pageNum }">
		<input type="hidden" name="amount" value="${cri.amount }">
		<textarea name="up_content" id="append" style="display: none;"></textarea>
	</form>
	<!-- <button id="resetBtn">지우기</button> -->
</body>
</html>