<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.petmu.freeBoard.model.vo.*"%>
    
 <% 
 	freeBoard fb = (freeBoard)request.getAttribute("freeBoard"); 
 %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : 게시글 수정</title>
<script src="<%= request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<!-- CSS 적용 -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/FboardUpdate.css" />
<!-- 타이틀 로고 -->
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath()%>/resources/images/petmu.ico" />
<!-- awesome 아이콘 cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" />
<!-- 구글폰트 cdn -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<!-- CK클래식 에디터 -->
<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="outer">

		<div class="category">
			<i class="far fa-edit"></i> <label id="cate">게시글 수정</label> <br>
			<br>
		</div>

		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/update.fb"
				id="updateForm" name="updateForm" method="post" enctype="multipart/form-data">
			
				<input type="hidden" name="bno" value="<%= fb.getBno()%>">  
				<input type="text" id="btitle" name="btitle" value="<%= fb.getbtitle() %>" maxlength="30">
				
				<br> <br>

				<textarea id="bcontent" name="bcontent"><%=fb.getBcontent() %></textarea>
				<br />
				<input type="file" name="bfile" id="bfile" />
				<br>
				<div class="btnArea" align="center">
					<button type="button" name="submitBtn" id="submitBtn">수정완료</button>
					<button type="button" name="cancelBtn" id="cancelBtn"
						onclick="location.href='<%=request.getContextPath()%>/selectList.fb'"
					>수정취소</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
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
			    	document.querySelector( '#updateForm' ).submit();
			    }
			    
			    
			} );
	</script>
</html>