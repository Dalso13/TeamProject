<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="../../../resources/css/base.css">
    <style>


    </style>
</head>
<body>
    <%@include file="../include/logoSerach.jsp"%>
    <br>
    <div id="body">
        <c:choose>
            <c:when test="${empty countryPage}">
                <h1>죄송합니다 ${reCountry} 에 대한 내용은 준비되지 않았습니다.</h1>
            </c:when>
            <c:otherwise>
                <h1>${countryPage.country} 둘러보기</h1>
                <button id="modify">수정</button>
                <span>
                    <button>호텔</button>
                    <button>맛집</button>
                    <button>관광지</button>
                 </span>
                <hr>
                <div id="titleImg"></div>
                <br>
                <div id="content">
                        ${countryPage.content}
                </div>
                <div id="userPost">
                    <div>유저 게시글</div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <script !src="">
        const imgEncodeUrl = '${countryPage.c_img}';

        document.getElementById("titleImg").innerHTML =
            `<img src="/country/viewImg?filename=\${encodeURIComponent(imgEncodeUrl)}" width="300px">`;

        $("#modify").on('click',()=>{
            location.href = "modify/${countryPage.country}";
        })
    </script>

</body>
</html>