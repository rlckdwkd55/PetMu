<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <title>PETMU : 회원탈퇴</title>
     <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
     <script src="https://kit.fontawesome.com/c10cbac54f.js" crossorigin="anonymous"></script>
 
 
 
 
</head>

<header style="">   <%@ include file="/views/common/header.jsp" %> </header>
<body style="min-width: 1000px; background-color:#f5f6f7; margin-left: 150px; margin-right: 130px;">
    
    <br><br>    <h1> <label class="fas fa-user-alt-slash" style="color: coral;"  ></label> 회원탈퇴</h1>


<hr style="border-width:1px;">
<br><br><br>
<label> <label  class="fas fa-check" style="color: coral;" ></label> <label style="font-weight: 600;">사용하고 계신 아이디의 탈퇴가 진행되면, 재사용 및 복구가 불가능합니다.</label> <br><br>
<label style="color:coral"> &nbsp;&nbsp;&nbsp; 탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가</label><label style="color: gray;">하오니 신중하게 선택하시기 바랍니다.</label> 
</label>

<br><br><br><br>

<label><label  class="fas fa-check" style="color: coral;" ></label> <label style="font-weight: 600;">탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.</label> <br><br>
&nbsp;&nbsp;&nbsp; <label style="color: gray;">자유게시판, 사진게시판 등에 올린 게시글 및 댓글은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다. <br>
&nbsp;&nbsp;&nbsp; 삭제를 원하는 게시글이 있다면 반드시 </label><label style="color:coral"> 탈퇴 전 삭제하시기 바랍니다.</label> <br>
&nbsp;&nbsp;&nbsp; <label style="color: gray;">탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 임의로 삭제해드릴 수 없습니다.</label>
</label>
<br><br><br><br>
<label>
<label  class="fas fa-check" style="color: coral;" ></label> <label style="font-weight: 600;">탈퇴 후에는 해당 아이디로 다시 가입할 수 있으며 다른 데이터는 복구할 수 없습니다.<br>
&nbsp;&nbsp;&nbsp; 게시판형 서비스에 남아 있는 게시글은 탈퇴 후 삭제할 수 없습니다.</label>
</label>
<br><br><br><br>
   
<label>&nbsp;&nbsp;&nbsp; 개인정보 보호를 위해 아이디와 비밀번호를 확인해주세요.</label> <br>
<form action="/PetMu/delete.do" method="post" id="deleteForm">
&nbsp;&nbsp;&nbsp; <input type="text" name="userId" id="userId" placeholder=" ID" style="width: 300px; height: 27px; margin-top: 10px;"> <br />
&nbsp;&nbsp;&nbsp; <input type="password" name="userPwd" id="userPwd" placeholder=" PASSWORD" style="width: 300px; height: 27px; margin-top: 10px;"> 
<br><br><br><br>

&nbsp;&nbsp;&nbsp; <label onclick="userDelete()" id="userDelete" style="color: coral; font-size: 20px; font-weight: 600; cursor: pointer;"> 탈퇴하기 </label>
</form>

<br><br><br><br><br>
</body>

<footer>

 <%@ include file="/views/common/footer.jsp" %>

</footer>

<script>
function userDelete(){
	
	
	$('#deleteForm').submit();
}

</script>

</html>