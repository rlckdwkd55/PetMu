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
border-width: 1px;


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
    background-color: black;
    color: white;
 

}

#pwSerch{

    background-color : white;
    border-color: white;
    color: gray;

}

.input{

    width: 550px;
    height: 50px;
    font-size: 25px;
    border-radius: 10px;
    border-width: 1px;
    border-style: solid;

}

#findId{


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
    <h2 align="center" style="margin-top: 150px;"><a href="../../index.jsp"> <img width="250" height="100" src="<%=request.getContextPath()%>/resources/images/petmuT.png"alt="펫뮤" > </a><br><br> ID / PW 찾기 </h2>
    <br><hr><br>

<div style="text-align:center">
    <br><br><br>
    <span align="center" class="select">

        <span class="serch" id="idSerch">아이디</span>
        <a href="pwSerch_page.jsp"><span class="serch" id="pwSerch">비밀번호</span></a>
</div>

    </span>
<br><br>
<form action="/PetMu/idserch.do"  method="post" id="idSerchForm" style="text-align: center; ">
    <h2 style="color: gray; font-weight: 500; font-size: 20px;">회원 가입 시 작성한 이름과 E-Mail을 입력해주세요.</h2> 

    <input type="text" id="userName" name="userName" class="input" placeholder=" 이름" > <br><br>
    <input type="text" id="email" name="email" class="input" placeholder=" E-Mail">
    
    <br><br><br>

    <label id="findId" style="cursor: pointer;"  >아이디 찾기</label>
    <br><br><br>

</form>





</body>
<footer style="margin-top:100px">  <%@ include file="/views/common/footer.jsp" %>	</footer>

<script>


	$('#findId').click(function(){
		
		var params ={
				userName : $("#userName").val(),
				email : $("#email").val()
				
				
		}
		
		
	 $.ajax({

		 type: 'post',
		 url : '/PetMu/idserch.do',
		 data : params,
		 success : function( data ) {
			 
		 if (data == 'null') {
				 alert("찾으시는 아이디가 없습니다!");
			 } else {
				 alert("찾으시는 아이디는 " + data + "입니다.");
			 }
		 }, error : function( code ) {
			 console.log( code );
		 }
	 });
});
 


















</script>

</html>