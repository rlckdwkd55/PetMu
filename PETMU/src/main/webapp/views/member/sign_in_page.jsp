<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
     <title>PETMU : 회원가입</title>
     <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
     <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
     <script src="https://kit.fontawesome.com/c10cbac54f.js" crossorigin="anonymous"></script>

     <style>
          #join {
               font-weight: 500;
         
          
          }

          #idCheck,
          #ckZip,
          #joinBtn, 
          #nicknameCheck {
             
               border-radius: 5px;
               width: 80px;
               height: 25px;
          }

          #userId,
          #userPwd,
          #userPwd2,
          #nickname,
          #userName,
          #zipCode,
          #address1,
          #address2,
          #email {
               width: 400px;
               height: 35px;

          }



          #gender {

               width: 408px;
               height: 41px;
          }

          #yy {
               width: 126px;
               height: 35px;
          }

          #mm {
               width: 127px;
               height: 41px;
          }

          #dd {
               width: 127px;

               height: 35px;
          }
          
          #idCheck:hover, #nicknameCheck:hover, #ckZip:hover, #joinBtn:hover, #emailCheck:hover {
		cursor:pointer;
	}

     .input{
 
          position: relative; bottom: 15px;
          
     }
          
     </style>
</head>

<body style="background-color:#f5f6f7">

     <h2 align="center" style="margin-top: 80px;"><a href="../../index.jsp"> <img width="250" height="100" src="<%=request.getContextPath()%>/resources/images/petmu.png" alt="펫뮤" > </a><br><br> 회원가입</h2>

     <hr>
     <section>
          <br>



          <form action="/PetMu/insert.do" method="post" id="joinForm" style="text-align: center; ">



               <div id="id_area">
               <div id="id" ><h3 id="join" align="center" style="padding-right: 350px;">아이디</h3></div>   
               <div><input class="input" style="margin-left : 88px" type="text" name="userId" id="userId" placeholder=" * 숫자 또는 영문자 사용가능, 5~12자리 이내" required="required"> 
                     <label id="idCheck" class="fas fa-check" style="font-size: 30px; color: coral; position: relative; bottom:13px;"></label></div>
              
               </div>
               


               <h3 id="join" align="center" style="padding-right: 330px;">비밀번호</h3>
               <input  class="input" type="password" id="userPwd" name="userPwd" placeholder=" * 숫자, 영문자, 특수문자 포함 8~16자리 이내"
                    required="required">


               <h3 id="join" align="center" style="padding-right: 289px;">비밀번호 확인</h3>
               <input  class="input" type="password" id="userPwd2" name="userPwd2">


               <h3 id="join" align="center"  style="padding-right: 350px;" >닉네임</h3>
               <div><input  class="input" style="margin-left : 88px" type="text" id="nickname" name="nickname" placeholder=" * 최대 8글자 이내" maxlength = 8 required="required">  <label id="nicknameCheck"  class="fas fa-check" style="font-size: 30px; color: coral; position: relative; bottom:13px;"></label></div>
               


               <h3 id="join" align="center"  style="padding-right: 373px;">이름</h3>
               <input   class="input" type="text" maxlength="14" id="userName" name="userName" required="required">


               <h3 id="join" align="center" style="padding-right: 330px;">생년월일</h3>


               <div class="join_row join_birthday">
                    <span class="ps_box">
                         <input  class="input" type="date" name="birth" id="birth" class="date" required="required" style="width:405px; height:35px; text-align:center; font-size:17px; ">
                    </span>


               </div>


               <h3 id="join" align="center" style="padding-right: 350px;">이메일 </h3>
               <input  class="input" id=email type="email" name="email" style="margin-left : 38px">  <label id="emailCheck"  class="fas fa-check" style="font-size: 30px; color: coral; position: relative; left:25px; bottom:13px;"></label>


               <h3 id="join" align="center" style="padding-right: 373px;">주소 </h3> 
               <div><input  class="input" style="margin-left: 88px;" type="text" id="zipCode" name="zipCode">  
                    <label class="fas fa-search-location" style="font-size: 30px; color: coral; position: relative; bottom:10px;" id="ckZip" onclick="addrSearch(); "></label> </div>
               <br>
               <input  class="input" type="text" id="address1" name="address1">
               <br><br>
             <input  class="input" type="text" id="address2" name="address2">
<br><br>
             <h3 style="font-weight: 500;" >반려동물을 키우고 계신가요?</h3>
             <input type="checkbox" id="petType" name="petType" value="강아지"> 강아지 &nbsp;
             <input type="checkbox" id="petType" name="petType" value="고양이"> 고양이 &nbsp;
             <input type="checkbox" id="petType" name="petType" value="기타"> 기타 &nbsp;
             <input type="checkbox" id="petType" name="petType" value="없음"> 없음 &nbsp;
             
              <br><br>
            <label style="font-size: 18px;">반려동물명 : </label> <input type="text" name="petName" value="" placeholder=" 직접 입력해주세요!" style="width: 200px; height: 35px; font-size: 18px;"> 





               <br>
               <div class="btns" align="center">
                    <br><br><br>
                    <label class="fas fa-sign-in-alt" id="joinBtn" onclick="insertMember();" style="width: 160px; color: coral; font-size: 30px;">가입하기!</label>
               </div>
          </form>

          <br><br>

     </section>
     <script>
   
   
   function addrSearch() {
          new daum.Postcode({
              oncomplete: function(data) {
                  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
  
                  // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                  // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                  var fullAddr = ''; // 최종 주소 변수
                  var extraAddr = ''; // 조합형 주소 변수
  
                  // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                  if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                      fullAddr = data.roadAddress;
  
                  } else { // 사용자가 지번 주소를 선택했을 경우(J)
                      fullAddr = data.jibunAddress;
                  }
  
                  // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                  if(data.userSelectedType === 'R'){
                      //법정동명이 있을 경우 추가한다.
                      if(data.bname !== ''){
                          extraAddr += data.bname;
                      }
                      // 건물명이 있을 경우 추가한다.
                      if(data.buildingName !== ''){
                          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                      }
                      // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                      fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                  }
  
                  // 우편번호와 주소 정보를 해당 필드에 넣는다.
                  $('#zipCode').val(data.zonecode); //5자리 새우편번호 사용
                  
                  $('#address1').val(fullAddr);
  
                  // 커서를 상세주소 필드로 이동한다.
                  $('#address2').focus();
              }

          }).open();
      };

      function insertMember() {
		$("#joinForm").submit();
	}
	
	$("#joinForm").submit(function(event){

		 var password = $("#userPwd").val();
    	 var id = $("#userId").val();
    	 var email = $("#email").val();
    	
    	 var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{4,12}$/;
    	 var pwPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{7,16}$/;
    	 var emailPattern = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    	 
    	 
    	 
		
		if($("#userPwd").val() == "" || $("#userId").val() == ""|| $("#nickname").val() == "" || $("#userName").val() == "" || $("#birth").val() == "" || $("#email").val() == "" || $("#zipCode").val() == "" || $("#address1").val() == ""|| $("#address2").val() == ""	|| $("input:checkbox[name='petType']:checked").length == 0
		) alert("모든 입력사항을 기입해주세요.");
		
		
		else if($('#userPwd').val() != $('#userPwd2').val()) alert("비밀번호 확인 값과 다릅니다.");
		else if(false === idPattern.test(id)) alert('아이디는 5자 이상 12자 이하여야 하며, 특수문자를 포함할수 없습니다.'); 
		else if(false === pwPattern.test(password)) alert('비밀번호는 8자 이상이어야 하며, 숫자/영문자/특수문자를 포함해야합니다.');
		else if(false === emailPattern.test(email)) alert('올바른 이메일 형식이 아닙니다.');
		else return;
		event.preventDefault();
	});
	


     
     
	 
     $('#idCheck').on('click', function(){
		
    	 
    	 
    	 
    	 var id = $("#userId").val();
    	 var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{4,12}$/;
    	 
    	 
    	 if(false === idPattern.test(id)) alert('아이디는 5자 이상 12자 이하여야 하며, 특수문자를 포함할수 없습니다.'); 
    	 else if( $.ajax({
			 url : '/PetMu/idcheck.do',
			 type: 'post',
			 data : { userId : $('#userId').val() },
			 success : function( data ) {
				 
				 console.log(data);
			 if ( data == 1) {
					 alert("이미 사용 중인 아이디입니다.");
				 } else {
					 alert("사용 가능한 아이디입니다");
				 }
			 }, error : function( code ) {
				 console.log( code );
			 }
		 }));
    	else return;
 		event.preventDefault();
	});
     
     
     
     $('#nicknameCheck').on('click', function(){
    	 
    
		 $.ajax({
			 url : '/PetMu/nicknamecheck.do',
			 type: 'post',
			 data : { nickname : $('#nickname').val() },
			 success : function( data ) {
				 
				 console.log(data);
				 if( data == 1) {
					 alert("이미 사용 중인 닉네임입니다.");
				 } else {
					 alert("사용 가능한 닉네임입니다");
				 }
			 }, error : function( code ) {
				 console.log( code );
			 }
		 });
	});
     
     
     
     $('#emailCheck').on('click', function(){
    	 
    	 var email = $("#email").val();

    	 var emailPattern = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    	 
    	 
    	 if(false === emailPattern.test(email)) alert('올바른 이메일 형식이 아닙니다.');
    	 else if(
		 $.ajax({
			 url : '/PetMu/emailcheck.do',
			 type: 'post',
			 data : { email : $('#email').val() },
			 success : function( data ) {
				 
				 console.log(data);
				 if( data == 1) {
					 alert("이미 사용 중인 이메일입니다.");
				 } else {
					 alert("사용 가능한 이메일입니다");
				 }
			 }, error : function( code ) {
				 console.log( code );
			 }
		 }));
		 else return;
	 		event.preventDefault();
	});
     
     
     </script>
</body>

</html>