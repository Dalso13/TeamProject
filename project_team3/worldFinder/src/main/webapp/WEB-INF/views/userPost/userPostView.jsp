<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    
    function focusEditor() {
      editor.focus({preventScroll: true});
    }
    
    // 에디터에 내용을 보여주기 위해 ${list.up_content} 값을 삽입
    var content = '${list.up_content}';
    editor.innerHTML = content;

    // 게시글 수정 버튼 클릭 시 에디터 내용을 textarea에 값으로 옮겨주는 함수
    /* function updateContent() {
      var content = editor.innerHTML;
      var processedContent = content.replace(/<img.*?alt="\[IMG\](.*?)".*?>/g, function(match, p1) {
        return '<img src="' + p1 + '" alt="이미지">';
      });
      $('#up_content').val(processedContent);
    } */

    // 게시글 수정 버튼 클릭 이벤트
    /* $('#update-btn').click(function() {
      updateContent();
    }); */
  });
</script>
<style type="text/css">
div#editor {
	border: 1px solid #D6D6D6;
	border-radius: 4px;
	min-height: 200px; /* 에디터의 최소 높이 설정 */
}

#editor img {
	max-width: 60%;
}
</style>
</head>
<body>
	<button id="mainBtn" onclick="location.href='/userPost/main'">목록으로 이동</button>
	<div>
		제목 
		<input name="title" value="${list.title }" readonly="readonly" />
		<br> 
		작성자 <input name="u_writer" value="${list.u_writer }" readonly="readonly" /> 
		<br> 
		내용
		<hr>
		<div id="editor" contenteditable="false"></div>
		<br>
		<button id="update-btn">게시글 수정</button>
		<form action="/userPost/update" method="post">
			<input type="hidden" name="up_idx" value="${list.up_idx }">
			<input type="hidden" name="up_content" id="up_content" value="">
		</form>
	</div>
</body>
</html>
