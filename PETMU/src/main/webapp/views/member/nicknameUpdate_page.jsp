<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ page import="com.kh.petmu.member.model.vo.Member" %>
 <%
 	Member m = (Member)session.getAttribute("member");
 %>
<!DOCTYPE html>
<html>
<head>
  <title>PETMU : 닉네임 변경</title>

  <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://kit.fontawesome.com/c10cbac54f.js" crossorigin="anonymous"></script>
</head>
<body>
    
<body style="background-color:#f5f6f7">

    <h2 align="center" style="margin-top: 200px;"><a href="../../index.jsp"> <img width="250" height="100" src="<%=request.getContextPath()%>/resources/images/petmu.png"alt="펫뮤" > </a><br><br> 닉네임 변경</h2>


    <hr>
    <br><br>
    <form action="/PetMu/nicknameUpdate.do" method="post" id="updateForm" style="text-align: center;">
   <div style="display: table;  margin: auto;">
        <table style="width: fit-content; padding-left: 60px;"  align="center">
        <tr>
    <td align="left" colspan="3">
    <h3 id="join">닉네임</h3></td>
    <td align="right" colspan="1" style="font-size: 20px; color:gray"><%= m.getNickname() %></td>
</tr>
    <tr><td colspan="4"><input  class="input" style="width: 300px; height: 35px; font-size: 20px;" type="text" id="nickname" name="nickname" required="required"  maxlength = 8 placeholder=" *변경할 닉네임, 최대 8글자"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;<label id="nicknameCheck"  class="fas fa-check" style="font-size: 30px; color: coral; position: relative; bottom:3px; cursor: pointer;"></label></td></tr>
</table>
</div>
</form>



<div class="btns" align="center">
    <br><br><br>
    <label class="fas fa-sign-in-alt" id="updateBtn" onclick="nicknameUpdate();" style="width: 160px; color: coral; font-size: 30px; cursor: pointer;">수정완료</label>
 </div>

<script>
function nicknameUpdate() {
	$("#updateForm").submit();
}

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


</script>

</body>

<footer style="margin-top:200px">  <%@ include file="/views/common/footer.jsp" %>	</footer>


</html>