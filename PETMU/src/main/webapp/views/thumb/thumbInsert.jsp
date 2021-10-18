<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : 게시글 작성</title>
<script src="<%= request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<!-- CSS 적용 -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/thumbInsert.css" />
<!-- 타이틀로고 -->
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/resources/images/petmu.ico" /> 
<!-- fontawesome 아이콘cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" /> 
<!-- 구글폰트 cdn -->
<link rel="preconnect" href="https://fonts.googleapis.com"> 
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<!-- CK클래식 에디터 -->
<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
</head>
<body>
	 <%@ include file="../common/header.jsp" %>

	<% if ( m != null ) { %>
	
	<section>
		<br>
		<br>
		<div class="category">
			<i class="far fa-edit"></i> 
			<label id="cate">게시글 작성</label><br><br> 
		</div>
		
	<div id="outline">
    <form id="insertForm" name="insertForm" method="post"
    		action="<%= request.getContextPath() %>/insert.tn" enctype="multipart/form-data">
        <div class="insertArea">
        <!-- 게시글 추가 영역 -->
        <input type="hidden" name="bwriterId" value="<%= m.getUserId() %>" />
        <input type="hidden" name="bwriterNick" value="<%= m.getNickname() %>" />
        <input id="btitle" type="text" name="btitle" placeholder="제목을 입력해주세요" required maxlength="50"><br><br>
        <textarea name="bcontent" id="bcontent"></textarea>
    <br />
    <table> 
        <tr>
            <td>
                <div id="titleImgArea">
                    <img id="titleImg" width="319" height="250">
                    <span id="thumbTitle">대표사진</span>
                </div>
            </td>
            <td>
                <div id="contentImgArea1">
                    <img id="contentImg1" width="319" height="250">
                </div>
            </td>
            <td>
                <div id="contentImgArea2">
                    <img id="contentImg2" width="319" height="250">
                </div>
            </td>
            <td>
                <div id="contentImgArea3">
                    <img id="contentImg3" width="319" height="250">
                </div>
            </td>         
        </tr>
    </table>
    </div>

    <div class="fileArea" id="fileArea">
        <!-- 첨부할 사진 추가 영역 -->
        <input type="file" name="thumbnailImg1" id="thumbnailImg1" onchange="loadImg(this,1);" />
        <input type="file" name="thumbnailImg2" id="thumbnailImg2" onchange="loadImg(this,2);" />
        <input type="file" name="thumbnailImg3" id="thumbnailImg3" onchange="loadImg(this,3);" />
        <input type="file" name="thumbnailImg4" id="thumbnailImg4" onchange="loadImg(this,4);" />
    </div>
    
    <br /><br />

        <div class="btnArea">
            <button id="submitBtn" type="submit">게시글 등록</button> &nbsp;
            <button id="cancelBtn" type="reset"
            	onclick="location.href='<%=request.getContextPath()%>/selectList.tn'">작성 취소</button>
        </div>
    </form>
    </div>
    
	</section>	
	
	<script>
	// 사진 게시글 미리보기 기능 구현
	$(function(){
		$('#titleImgArea').click(function(){
			$('#thumbnailImg1').click();
		});
		
		$('#contentImgArea1').click(function(){
			$('#thumbnailImg2').click();
		});
		
		$('#contentImgArea2').click(function(){
			$('#thumbnailImg3').click();
		});
		
		$('#contentImgArea3').click(function(){
			$('#thumbnailImg4').click();
		});
		
		$('#fileArea').hide();
	})
	
	function loadImg(img, num){
		if(img.files && img.files[0]) {
			
			var reader = new FileReader();
			
			reader.onload = function(e){
				
				switch(num){
				case 1 : $('#titleImg').attr('src', e.target.result);
						 break;
				case 2 : $('#contentImg1').attr('src', e.target.result);
						 break;
				case 3 : $('#contentImg2').attr('src', e.target.result);
						 break;
				case 4 : $('#contentImg3').attr('src', e.target.result);
						 break;
				}
			}
			
			reader.readAsDataURL(img.files[0]);
		}	
	}
	</script>
	<% } %>

	<br />
	<%@ include file="../common/footer.jsp" %>
</body>
<script>

// CK에디터, 작성버튼 함수
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
	    	document.querySelector( '#insertForm' ).submit();
	    }
	    
	    
	} );

	</script>

</html>



