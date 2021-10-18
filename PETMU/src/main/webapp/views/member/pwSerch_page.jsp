<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>PETMU : ID/PW 찾기</title>
   <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
   <script src="https://kit.fontawesome.com/c10cbac54f.js" crossorigin="anonymous"></script>

   <style>

.select{
width: 500px;
height: 35px;



}
.serch{
display: inline-block;
width: 280px;
height: 55px;
border-style: solid;
border-width: 1px;
font-size: 27px;
padding-top: 20px;
}

#idSerch{
    background-color: white;
    color: gray;
    border-color:white;
 

}

#pwSerch{

    background-color : black;
    color: white;
    border-color: black;

}

.input{

    width: 550px;
    height: 50px;
    font-size: 25px;
    border-radius: 10px;
    border-width: 1px;
    border-style: solid;

}

#changePw{


    width: 560px;
    height: 55px;
    background-color: rgb(255, 143, 16);
    display: inline-block;
    color: white;
    padding-top: 20px;
    font-size: 25px;

}



   </style>
</head>
<body  style="background-color:#f5f6f7;">
    <h2 align="center" style="margin-top: 150px;"><a href="../../index.jsp"> <img width="250" height="100" src="<%=request.getContextPath()%>/resources/images/petmu.png"alt="펫뮤" > </a><br><br> ID / PW 찾기 </h2>
    <br><hr><br>
<form action="/PetMu/changepw.do"  method="post" id="changePwForm" style="text-align: center; ">

    <br><br><br>
    <span align="center" class="select">

        <a href="idSerch_page.jsp"> <span class="serch" id="idSerch">아이디</span></a>
    <span class="serch" id="pwSerch">비밀번호</span>


    </span>
<br><br>

    <h2 style="color: gray; font-weight: 500; font-size: 20px;">회원 가입시 작성한 개인정보를 입력해주세요.</h2> 

    <input type="text" name = "userId" id="userId" class="input" placeholder=" 아이디" > <br><br>
    <input type="text" name = "userName" id="userName" class="input" placeholder=" 이름"><br><br>
    <input type="text" name = "email" id="email" class="input" placeholder=" E-Mail"><br>
   
    <br>
    <input type="password" name = "userPwd" id="userPwd" class="input" placeholder=" 변경하실 새로운 비밀번호를 입력해주세요."><br><br>
    <input type="password" name = "userPwd2" id="userPwd2" class="input" placeholder=" 변경하실 비밀번호 확인"><br> <br><br>

    <span id="changePw" onclick ="pwChange()" style="cursor: pointer;" >비밀번호 변경하기</span>
    <br><br><br><br><br>

</form>

</body>

<footer style="margin-top:100px">  <%@ include file="/views/common/footer.jsp" %>	</footer>
<script>

function pwChange() {
	$("#changePwForm").submit();
}

$("#changePwForm").submit(function(event){

	 var password = $("#userPwd").val();
	 var id = $("#userId").val();
	 var email = $("#email").val();
	
	 var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{5,12}$/;
	 var pwPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
	 var emailPattern = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	 
	 
	 
	
	if($("#userPwd").val() == "" || $("#userId").val() == ""|| $("#userName").val() == "" || $("#email").val() == "" 
	) alert("모든 입력사항을 기입해주세요.");
	
	
	else if($('#userPwd').val() != $('#userPwd2').val()) alert("비밀번호 확인 값과 다릅니다.");
	else if(false === idPattern.test(id)) alert('아이디는 5자 이상 12자 이하여야 하며, 특수문자를 포함할수 없습니다.'); 
	else if(false === pwPattern.test(password)) alert('비밀번호는 8자 이상이어야 하며, 숫자/영문자/특수문자를 포함해야합니다.');
	else if(false === emailPattern.test(email)) alert('올바른 이메일 형식이 아닙니다.');
	else return;
	event.preventDefault();
});



</script>



</html>