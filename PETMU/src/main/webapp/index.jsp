<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>PETMU</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/petcss.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/header.css" />
<!-- 타이틀 로고 -->
<link rel="shortcut icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/resources/images/petmu.ico" />
</head>


<body>

	<%@ include file="/views/common/header.jsp"%>

	<div id="best">
		<div class="bestboard">
			<img class="bestphoto"
				src="<%=request.getContextPath()%>/resources/images/1.jpg">
		</div>
		<div class="bestboard">
			<img class="bestphoto"
				src="<%=request.getContextPath()%>/resources/images/2.jpg">
		</div>
	</div>

	<h1 align="center">FOCUS ON</h1>

	<div class="container">
		<!-- 첫 번째 Modal을 여는 클래스 -->
		<div class="btn">
			<img src="<%=request.getContextPath()%>/resources/images/know1.png">
		</div>

		<!-- 첫 번째 Modal -->
		<div class="modal">

			<!-- 첫 번째 Modal의 내용 -->
			<div class="modal-content">
				<div class="close">&times;</div>
				<img src="<%=request.getContextPath()%>/resources/images/modal1.png">
			</div>
		</div>

		<!-- 두 번째 Modal을 여는 클래스 -->
		<div class="btn">
			<img src="<%=request.getContextPath()%>/resources/images/know2.png">
		</div>

		<!-- 두 번째 Modal -->
		<div class="modal">

			<!-- 두 번째 Modal의 내용 -->
			<div class="modal-content">
				<div class="close">&times;</div>
				<img src="<%=request.getContextPath()%>/resources/images/modal2.png">
			</div>
		</div>

		<!-- 세 번째 Modal을 여는 클래스 -->
		<div class="btn">
			<img src="<%=request.getContextPath()%>/resources/images/know3.png">
		</div>

		<!-- 세 번째 Modal -->
		<div class="modal">

			<!-- 세 번째 Modal의 내용 -->
			<div class="modal-content">
				<div class="close">&times;</div>
				<img src="<%=request.getContextPath()%>/resources/images/modal3.png">
			</div>
		</div>
	</div>
</body>
<script>
	// Modal을 가져옵니다.
	var modals = document.getElementsByClassName("modal");
	// Modal을 띄우는 클래스 이름을 가져옵니다.
	var btns = document.getElementsByClassName("btn");
	// Modal을 닫는 close 클래스를 가져옵니다.
	var spanes = document.getElementsByClassName("close");
	var funcs = [];

	// Modal을 띄우고 닫는 클릭 이벤트를 정의한 함수
	function Modal(num) {
		return function() {
			// 해당 클래스의 내용을 클릭하면 Modal을 띄웁니다.
			btns[num].onclick = function() {
				modals[num].style.display = "block";
				console.log(num);
			};

			//  <span> 태그(X 버튼)를 클릭하면 Modal이 닫습니다.
			spanes[num].onclick = function() {
				modals[num].style.display = "none";
			};
		};
	}

	// 원하는 Modal 수만큼 Modal 함수를 호출해서 funcs 함수에 정의합니다.
	for (var i = 0; i < btns.length; i++) {
		funcs[i] = Modal(i);
	}

	// 원하는 Modal 수만큼 funcs 함수를 호출합니다.
	for (var j = 0; j < btns.length; j++) {
		funcs[j]();
	}

	// Modal 영역 밖을 클릭하면 Modal을 닫습니다.
	window.onclick = function(event) {
		if (event.target.className == "modal") {
			event.target.style.display = "none";
		}
	};
</script>

<br>
<br>
<br>
<br>

<%@ include file="/views/common/footer.jsp"%>

</html>