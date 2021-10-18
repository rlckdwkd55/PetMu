<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>PETMU : 로그인</title>
   <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   <script src="https://kit.fontawesome.com/c10cbac54f.js" crossorigin="anonymous"></script>

   <style>

.txtin{
    width: 350px;
               height: 50px;
               border-radius: 10px;
               border-color: gray;

}

   </style>
</head>
<body style="background-color:#f5f6f7;  padding-left: 10px;">
   
    <h2 align="center" style="margin-top: 150px;"><a href="../../index.jsp"> <img width="250" height="100" src="<%=request.getContextPath()%>/resources/images/petmu.png" alt="펫뮤" > </a><br><br>비밀번호 변경이 완료되었습니다. 다시 로그인 해주세요. </h2>
    <br><hr><br><br>


    <form action="/PetMu/login.do"  method="post" id="loginForm" style="text-align: center; ">
    <br>
    <label  class="far fa-id-card" style="font-size: 35px; color: coral;  position: relative; top: 4px; font-weight: 900;" >&nbsp;</label>
    <input class="txtin" type="text" name="userId" placeholder=" ID" style="font-size: 25px; margin-right: 40px;">
<br><br>
    <label class="fas fa-key" style="font-size: 35px; color: coral; position: relative; top: 4px;" >&nbsp;</label>
    <input class="txtin" type="password" name="userPwd" placeholder=" PASSWORD" onkeyup="enterKet();" style="font-size: 25px;  margin-right: 35px;">
<br><br><br><br><br>
<label id="loginBtn" onclick="login()"  class="fas fa-power-off" style="font-size: 25px; margin-left: 40px;cursor: pointer;"> 로그인 &nbsp;</label>

<label style="font-size : 30px; "> | </label>

&nbsp;<label id="memberJoinBtn" onclick="memberJoin()" class="fas fa-sign-in-alt" style="font-size: 25px;cursor: pointer;"> 회원가입 &nbsp; </label> <br><br><br><br>
<label id="loginBtn" onclick="idSerch()"   style="font-size: 20px; font-weight: 800; color: gray; margin-left: 26px;cursor: pointer;"> 아이디 찾기</label>
&nbsp;&nbsp;
<label id="loginBtn" onclick="pwSerch()"  style="font-size: 20px; font-weight: 800; color: gray;cursor: pointer;"> 비밀번호 찾기</label>
<br /><br /><br /><br /><br />
    </form>


    <script>
		function enterKey(){
			if(window.event.keyCode == 13) {
				login();
			}
		}

		
		
		
        function login(){
		$('#loginForm').submit();
	}
        

    function memberJoin(){
		location.href="agreement_page.jsp";
	}

    function idSerch(){
		location.href="idSerch_page.jsp";
	}
    function pwSerch(){
		location.href="pwSerch_page.jsp";
	}


	</script>


</body>

<footer style="margin-top:100px">  <%@ include file="/views/common/footer.jsp" %>	</footer>
</html>