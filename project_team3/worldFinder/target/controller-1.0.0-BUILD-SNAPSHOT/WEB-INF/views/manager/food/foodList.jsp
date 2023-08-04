<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.foodList table{	/* 테이블 가운데 정렬 */
		margin: auto;
	}
	.pagination{	/* 페이징 버튼 css (ul) */
		list-style: none;
	}
	.pagination li{	/* 페이징 버튼 css (li) */
		background-color: black;
		font-size: 1.5em;
		float: left;
		margin-right: 20px;
		border: solid black 1px;
	}
	.pagination li a{	/* 페이징 버튼 css (a) */
		color: white;
		text-decoration-line: none;
	}

/* 구글맵에 마우스 오버시 지도 확대	
	.map {								
	  transition: all 0.2s linear;
	  z-index: 1;
	  width: 200px;
	  height: 160px;
	}
	.map:hover {
		position: absolute;
		z-index: 2;
		transform: scale(2);
 	 	width: 400px;
		height: 400px; 
		position: fixed; 
	} */
	
	.mapDetailButton{
		background-color: yellow;
		z-index: 3;
		position: relative;
		top: -160px;
		left: 5px;
	}
	.detail{	/* detial 클릭시 나오는 자세한 지도 */
		margin: auto;
		width: 1000px;
		height: 600px;
	}
	#detailMap{
		display: none;
	}
	th, td{
		border: solid black 1px;
	}
</style>
<!-- jQuery 사용 설치 파일 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- 구글 맵 api 사용 설치 파일 -->
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDMR_wbRSxhUfjlmG_Pbk6OHjr6mJvgkMI"></script>


</head>
<body>
	<h1>food 페이지</h1>
	
	<div class = "foodList" id="wholeDiv">
		<input type="button" value="등록하기" style="left: 75%;">
		<table style="border: solid black 1px;">
			<tbody>
				<c:if test="${not empty list}">
				<c:forEach var="food" items="${list }">
					<tr>
						<td>	<!-- 맛집 이미지 삽입 -->
							<img src="../../resources/img/${food.fp_Image }.jpg" width="200px" height="160px"/>
						</td>
						<td width="600px">	<!-- 상품에 대한 정보 (이름, 가격 등) -->
							<ul>
								<li>${food.fp_Name }</li>
								<li>${food.fp_Address }</li>
								<li>${food.fp_Menu }</li>
							</ul>
						</td>
						<td>	<!-- 구글맵 api -->
						<div class="${food.fp_Idx }" style="width: 200px; height: 160px;">
							<div id="${food.fp_Idx }" class="map" ad="${food.fp_Address }" tel="${food.fp_Tel }" name="${food.fp_Name }"
							style="width: 200px; height: 160px; z-index: 2;">
							</div>
							<input type="button" value="detail" class="mapDetailButton" id="${food.fp_Idx }"
							ad="${food.fp_Address }" tel="${food.fp_Tel }" name="${food.fp_Name }">
						</div>
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty list}">
					<tr>
						<td style="width: 400px; height: 50px;">
							빠른 시일 내에 추가 예정입니다! 감사합니다!
						</td>
					</tr>
				</c:if>
			</tbody>
			<tfoot>
				<tr><td colspan="4">
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
				</td></tr>
			</tfoot>
		</table>
		
		
		
		
		<form action="/manager/food/foodList" method="get" id="actionForm">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
		</form>
		
	</div>
	
	<div id="detailMap">
		<h3>자세한 지도</h3>
		<div class="detail" id="randomMap"></div>
		<input type="button" value="돌아가기" id="backList" style="float: right;">
	</div>
	
<!-- 페이징 처리 스크립트 -->
<script type="text/javascript">
	var actionForm = $("#actionForm"); //폼태그 정보 가져오기

	//페이징 버튼 이벤트 처리
	$(".paginate_button a").on('click', function(e){
		e.preventDefault();
		
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
</script>

<!-- 구글맵 api 스크립트 ********************************************* -->
<script type="text/javascript">

	var maps = [];
	var markers = [];
	
    function initialize() {

    	
        var mapOptions = {
                            zoom: 15, // 지도를 띄웠을 때의 줌 크기
                            disableDefaultUI:true,	//컨트롤러 제거
                            mapTypeId: google.maps.MapTypeId.ROADMAP
                        };
            
        
        var $maps = $('.map');
        
        
        $.each($maps, function (i, value) {
        
        	//구글맵 div 에서 받아온 요소 정의
        	var name = $(value).attr('name');
        	var ad = $(value).attr('ad');
        	var tel = $(value).attr('tel');
        	var idx = $(value).attr('id');
        	
        	//console.log("lat: " + name + ad + tel);
        	
        	makeMap(name, ad, tel, idx, mapOptions);
        
    	});
    }

    google.maps.event.addDomListener(window, 'load', initialize);
    
    
</script>

<script type="text/javascript">


	//자세한 구글 맵을 보여주는 버튼
	$(".mapDetailButton").click(function(){
		
		$("#wholeDiv").hide();
		$("#detailMap").show();
		
		
        var mapOptions = {
                zoom: 15, // 지도를 띄웠을 때의 줌 크기
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
		
		
		var idx = $(this).attr('id') + "detail";	
		var name = $(this).attr('name');
		var tel = $(this).attr('tel');
		var ad = $(this).attr('ad');
		
		document.querySelector('.detail').setAttribute("id", idx);
		
		console.log($(".detail").attr("id"));
		
		makeMap(name, ad, tel, idx, mapOptions);
		
		
	});
	
	
	$("#backList").click(function(){
		$("#wholeDiv").show();
		$("#detailMap").hide();
	});
	


</script>

<script type="text/javascript">

	/* 주소 입력받아 지도 만드는 함수 */
	function makeMap(name, ad, tel, idx, mapOptions){
		
		
		var size_x = 10; // 마커로 사용할 이미지의 가로 크기
        var size_y = 10; // 마커로 사용할 이미지의 세로 크기
     
        // 마커로 사용할 이미지 주소
        var image = new google.maps.MarkerImage( '../../resources/img/marker.png',
                                                    new google.maps.Size(size_x, size_y),
                                                    '',
                                                    '',
                                                    new google.maps.Size(size_x, size_y));
		
		var map = new google.maps.Map(document.getElementById(idx), // div의 id과 값이 같아야 함. "map-canvas"
                mapOptions);


		// Geocoding *****************************************************
		var address = ad; // DB에서 주소 가져와서 검색하거나 왼쪽과 같이 주소를 바로 코딩.
		var marker = null;
		var geocoder = new google.maps.Geocoder();
		geocoder.geocode( { 'address': address}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				map.setCenter(results[0].geometry.location);
				marker = new google.maps.Marker({
				                map: map,
				                icon: image, // 마커로 사용할 이미지(변수)
				                title: name, // 마커에 마우스 포인트를 갖다댔을 때 뜨는 타이틀
				                position: results[0].geometry.location
				            });
			
			var content = "" + name + "<br/><br/>Tel: " + tel; // 말풍선 안에 들어갈 내용
			
			// 마커를 클릭했을 때의 이벤트. 말풍선 뿅~
			var infowindow = new google.maps.InfoWindow({ content: content});
			google.maps.event.addListener(marker, "click", function() {infowindow.open(map,marker);});
			} else {
				alert("Geocode was not successful for the following reason: " + status);
			}
		});
			// Geocoding // *****************************************************
		
	}




</script>


</body>
</html>