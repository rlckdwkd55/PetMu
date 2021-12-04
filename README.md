# JSP SemiProject 'Petmu'

## [최종보고서 링크](report/JSP_Project_Petmu.pdf)
* 프로젝트 명 : 펫뮤(Petmu)

* 프로젝트 기간 : 2021-10-05 ~ 2021-10-15

* 프로젝트 설명

  - MVC model2(MVC 패턴) 기반으로 한 웹 페이지
  - 반려동물 보호자들에게 필요한 정보에 대해 공유할 수 있는 이용자 친화적인 커뮤니티 사이트
  - 회원가입, 게시판마다 CRUD 적용
  - AJAX, 외부 API(Kakao map API, Daum Postcode API)등 다양한 기술을 활용한 프로젝트
  
* 구현 기능

  - 인트로, 인덱스
  - 회원가입, 로그인, ID·PW 찾기, 마이페이지, 회원정보 수정, 회원탈퇴
  - 우리동네 동물병원(DB등록과 map API를 이용한 주변 병원 조회)
  - 후기·정보 게시판 / 자유게시판 (게시글 CRUD, 게시글 추천, 댓글 CRUD, 게시판 내 검색, 게시글 정렬)
  - 사진게시판 (게시글 CRUD, 게시글 추천, 댓글 CRUD, 게시판 내 검색, 게시글 정렬, 이미지 슬라이드)
  
* 사용기술 및 개발 환경

  - OS : Windows 10, MacOS
  - Developer-Tool : 
      VSCode / Eclipse / SQLDeveloper
  - Front-End : 
      HTML5 / CSS3 / JavaScript (ES8) / jQuery
  - Back-End :
      Java EE 11 / Oracle DBMS 11G R2(XE) / apache-tomcat 8.5
  - version control tools : 
      Egit (Eclipse Git) / GitHub

* 역할

  - 홈페이지 인트로
  - 인덱스
  - 우리동네 동물병원

## 구현기능 및 기능설명
### 홈페이지 인트로 
![intro](https://user-images.githubusercontent.com/83908822/144700359-bccd4e87-d2da-490d-a1d9-3f8df345e392.gif)

#
### 홈페이지 인덱스
![index](https://user-images.githubusercontent.com/83908822/144701130-4b439ac5-4cbd-4604-98c9-8c3dbd4ad24f.gif)
1. 로고 제작 후 세션정보를 받아올 수 있도록 회원가입과 로그인버튼 배치
2. 주요 버튼들 상단 네비게이션바에 배치, 편의성 증대
3. 하단 FOCUS ON 부분 이미지 제작 후 모달창으로 페이지 전환없이 반려동물을 키울 때 주의사항 배치

``` java
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
```

#

### 우리동네 동물병원 
![map](https://user-images.githubusercontent.com/83908822/144702222-c1bdd565-14f4-4153-9762-dc7d31af87df.gif)
1. 카카오지도API 사용
2. GeoLocation을 이용 위도, 경도를 얻어온 후 인포윈도우에 "현재위치" 로 표시
3. 지도 접속 시 해당 위치를 중심좌표로 설정 
4. 공공데이터를 받아온 후 지오코딩을 이용해 위도경도값을 추출, 지도에 병원정보를 
5. eventListener를 이용 이벤트값을 받아 마커위에 마우스가 위치하면 병원이름 표출, 나갈 시 사라짐

 ``` java
 navigator.geolocation.getCurrentPosition(function (position) {

				var lat = position.coords.latitude, // 위도
					lon = position.coords.longitude; // 경도

				var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
					message = '<div style="padding:5px;">현재위치</div>'; // 인포윈도우에 표시될 내용입니다

				// 마커와 인포윈도우를 표시합니다
				displayMarker(locPosition, message);

			});
			// 지도에 마커와 인포윈도우를 표시하는 함수입니다
			function displayMarker(locPosition, message) {

				// 마커를 생성합니다
				var marker_present = new kakao.maps.Marker({
					map: map,
					position: locPosition
				});

				var iwContent = message, // 인포윈도우에 표시할 내용
					iwRemoveable = true;

				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					content: iwContent,
					removable: iwRemoveable
				});

				// 인포윈도우를 마커위에 표시합니다 
				infowindow.open(map, marker_present);

				// 지도 중심좌표를 접속위치로 변경합니다
				map.setCenter(locPosition);
			}
		}
		
 ```
 
 #
 
 <img width="1014" alt="ThankYou" src="https://user-images.githubusercontent.com/83908822/144702680-48f443e4-8c0d-4e99-90bc-e42703a1be03.png">
