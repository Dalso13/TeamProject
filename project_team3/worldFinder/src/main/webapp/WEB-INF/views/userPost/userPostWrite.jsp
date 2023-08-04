<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	});
</script>
<style type="text/css">
	div#editor {
		border: 1px solid #D6D6D6;
		border-radius: 4px;
	}

	#editor img {
		max-width: 60%;
	}
	
	/* #btn-img {
		display: none;
	} */
</style>
</head>
<body>
	<button id="mainBtn" onclick="location.href='/userPost/main'">목록으로 이동</button>
	<form action="/userPost/write" method="post" role="form">
		<input type="text" placeholder="제목을 입력하세요." name="title">
		<hr>
		<button type="button" id="btn-img">IMG<!-- <img src="/resources/image/img-icon.png"/> --></button>
		<br><br>
		<div id="editor" contenteditable="true"></div>
		<input id="img-selector" type="file" style="display: none;">
		<br>
		<input type="submit" id="post-btn"value="게시글 등록">

		<input type="hidden" name="u_writer" value="tw123">
		<input type="hidden" name="country" value="대한민국">
		<textarea name="up_content" id="append" style="display: none;"></textarea>
	</form>
</body>
</html>