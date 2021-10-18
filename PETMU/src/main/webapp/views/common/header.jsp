<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.petmu.member.model.vo.Member"%>
<%
Member m = (Member) session.getAttribute("member");
%>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">

<header>
	<div id="logo">
		<img src="<%=request.getContextPath()%>/resources/images/petmu.png"
			align="left" onclick="goHome();" width="130" height="46" id="logo">
	</div>
	<%
	if (m == null) {
	%>
	<div id="top_menu">
		<div class="top" onclick="memberJoin()">회원가입 |</div>
		<div class="top" onclick="login()">로그인</div>


	</div>
	<%
	} else {
	%>
	<div id="top_menu">

		<div class="top"><%=m.getNickname()%>
			|
		</div>
		<div class="top" onclick="myPage();">마이페이지 |</div>
		<div class="top" onclick="logout()">로그아웃 |</div>


	</div>
	<%
	}
	%>


	<div id="menu">
		<nav>
			<div class="menu" onclick="all();">≡ 전체 카테고리</div>
			<div class="menu" onclick="hospital();">우리동네 동물병원</div>
			<div class="menu" onclick="information();">후기⋅정보</div>
			<div class="menu" onclick="freeboard();">자유게시판</div>
			<div class="menu" onclick="thumb();">나의 댕냥 자랑</div>
		</nav>
	</div>
	
   <div style="position: fixed; bottom: 15px; right: 10px;">
      <a href="#top"> <img title="위로가기"
         src="https://img.icons8.com/ios-glyphs/40/000000/double-up--v1.png" /></a>
   </div>
	
	<%
	if (m != null) {
	%>



	<script>

        function logout() {
            location.href = "<%=request.getContextPath()%>/logout.do";
        }

        function myPage() {
            location.href = "<%=request.getContextPath()%>/personallist.do?nickname=<%=m.getNickname()%>";
        }


        function goHome() {
            location.href = "<%=request.getContextPath()%>/index.jsp";
        }

        function hospital() {
            location.href = "<%=request.getContextPath()%>/views/hospital/hospital.jsp";
        }

        function information() {
        	 location.href = "<%=request.getContextPath()%>/selectList.bo?cate=3";
        }

        function freeboard() {
            location.href = "<%=request.getContextPath()%>/selectList.fb";
        }

        function thumb() {
            location.href = "<%=request.getContextPath()%>/selectList.tn";
   
        }
        
        
        </script>

	<%
	} else {
	%>

	<script>
        
            function login() {
                location.href = "<%=request.getContextPath()%>/views/member/login_page.jsp";
            }
            
            function memberJoin() {
                location.href = "<%=request.getContextPath()%>/views/member/agreement_page.jsp";
            }


            function goHome() {
                location.href = "<%=request.getContextPath()%>/index.jsp";
            }

            function hospital() {
                location.href = "<%=request.getContextPath()%>/views/hospital/hospital.jsp";
            }

            function information() {
            	 location.href = "<%=request.getContextPath()%>/selectList.bo?cate=3";
            }

            function freeboard() {
                location.href = "<%=request.getContextPath()%>/selectList.fb";
            }

            function thumb() {
                location.href = "<%=request.getContextPath()%>/selectList.tn";

		}
	</script>

	<%
	}
	%>




</header>