<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.petmu.board.model.vo.*, java.util.*"%>
    
<%
	Board b = (Board)request.getAttribute("board");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : 게시글 수정</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sideMenu.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/boardUpdate.css" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath()%>/resources/images/petmu.ico" /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" />
<script src="<%= request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>

<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>



</head>
<body>
<%@ include file="/views/common/header.jsp" %>
    <div class="main">

        <%@include file="/views/common/sideMenu.jsp" %>

        <div class="boardArea">
            <div id="boardTitle" style="font-family: 'Jua', sans-serif; font-size : 30px;">
                <label>게시글 수정</label>
                <p style="font-size : 20px;">[병원 / 약국 정보 공유]</p>
            </div>
            <div id="content">

                 <form action="<%= request.getContextPath() %>/update.bo?cate=3" method="post" id="frm"> <%--cate check --%>
                	<input type="hidden" name="bno" value="<%= b.getBno() %>" />
                    <div id="top">
	                    <input type="text" name="btitle" id="btitle" placeholder=" 제목을 입력해 주세요." value="<%= b.getBtitle() %>">
	                    <%--b.gettitle --%>
	                    <button type="button" name="submitBtn" id="submitBtn">수정</button>                    
                    </div>
                    <textarea name="bcontent" id="bcontent" cols="100" rows="10" style="height:650px;"><%= b.getBcontent() %></textarea>
                    
                </form>

            </div>
    	</div>
    </div>
    <%@ include file="/views/common/footer.jsp" %>
</body>

<script>
	var eidtor
	ClassicEditor
	   .create( document.querySelector( '#bcontent' ) )
	   .then( newEditor => {
	       editor = newEditor;
	   } )
	   .catch( error => {
	       console.error( error );
	   } );
	
	
	document.querySelector( '#submitBtn' ).addEventListener( 'click', () => {
	    var editorData = editor.getData();
	    var btitle = document.querySelector( '#btitle' ).value;
	    
	    console.log(btitle);
	    console.log(editorData);
	    
	    if(!btitle) {
	    	alert("제목을 입력해 주세요.");
	    } else if(!editorData) {
	    	alert("내용을 입력해 주세요.");
	    } else {
	    	document.querySelector( '#frm' ).submit();
	    }
	    
	    
	} );
	
</script>


</html>









