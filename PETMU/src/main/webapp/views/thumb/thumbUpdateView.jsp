<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.petmu.thumb.model.vo.*"%>
<%
	Thumbnail t = (Thumbnail)request.getAttribute("thumbnail");
	ArrayList<Attachment> fileList
	   = (ArrayList<Attachment>)request.getAttribute("fileList");
	
	Attachment titleImg = fileList.get(0);
	Attachment detailImg1 = fileList.get(1);
	Attachment detailImg2 = fileList.get(2);
	Attachment detailImg3 = fileList.get(3);	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : 게시글 수정</title>
<script src="<%= request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<!-- CSS 적용 -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/thumbUpdate.css" />
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
	<section>
	<br><br>
		<div class="category">
			<i class="far fa-edit"></i> <label id="cate">게시글 수정</label><br>
			<br>
		</div>
		
		<div id="outline">
		<form id="updateForm" action="<%= request.getContextPath() %>/update.tn" method="post" enctype="multipart/form-data">
			<div id="updateArea"> 
				<!-- 게시글 수정 영역 -->
				<input type="hidden" name="bno" value="<%= t.getBno() %>"/>
				<input type="text" id="btitle" name="btitle" value="<%= t.getbtitle() %>"/><br><br>
				<textarea name="bcontent" id="bcontent"><%= t.getBcontent() %></textarea>
				<br />
				<table>
					<tr>
						<td>
							<div id="titleImgArea">
								<img id="titleImg" width="319" height="250"
								 src="<%= request.getContextPath()%>/resources/thumbFiles/<%= titleImg.getChangename() %>">           
								<span id="thumbTitle">대표사진</span>
							</div>
						</td>
					
						<td>
							<div id="contentImgArea1">
							<% if (detailImg1.getChangename() != null) { %>
								<img id="contentImg1" width="319" height="250"
								     src="<%=request.getContextPath() %>/resources/thumbFiles/<%= detailImg1.getChangename() %>">
							<% } else { %>
								<img id="contentImg1" width="319" height="250" src="<%= request.getContextPath() %>/resources/images/no-image.png">
							<% } %>
							</div>
						</td>
						
						<td>
							<div id="contentImgArea2">
							<% if (detailImg2.getChangename() != null) { %>
								<img id="contentImg2" width="319" height="250"
									src="<%=request.getContextPath() %>/resources/thumbFiles/<%= detailImg2.getChangename() %>">
							<% } else { %>
								<img id="contentImg2" width="319" height="250" src="<%= request.getContextPath() %>/resources/images/no-image.png">
							<% } %>
							</div>
						</td>
						
						<td>
							<div id="contentImgArea3">
							<% if (detailImg3.getChangename() != null) { %>
								<img id="contentImg3" width="319" height="250"
									src="<%=request.getContextPath() %>/resources/thumbFiles/<%= detailImg3.getChangename() %>">
							<% } else { %>
								<img id="contentImg3" width="319" height="250" src="<%= request.getContextPath() %>/resources/images/no-image.png">
							<% } %>
							</div>
						</td>
					</tr>
		      	</table>
			</div>
			
			<div class="fileArea" id="fileArea">
				<!-- 첨부 사진 추가 영역 -->
				<input type="file" accept="image/*" name="thumbImg1" id="thumbImg1" onchange="loadImg(this,1);" />
				<input type="file" accept="image/*" name="thumbImg2" id="thumbImg2" onchange="loadImg(this,2);" />
				<input type="file" accept="image/*" name="thumbImg3" id="thumbImg3" onchange="loadImg(this,3);" />
				<input type="file" accept="image/*" name="thumbImg4" id="thumbImg4" onchange="loadImg(this,4);" />
			</div>
			
			<br><br>
			
			<div class="btnArea">
				<button id="updateBtn" type="submit">수정 완료</button> &nbsp;
				<button id="cancelBtn" type="button" onclick="goDetail();">수정 취소</button>
			</div>
			
		</form>
		</div>
		
	<br><br>
	</section>
	<%@ include file="../common/footer.jsp" %>
</body>
		<script>
		// 수정취소 버튼
		function goDetail(){
			location.href="<%= request.getContextPath()%>/selectOne.tn?bno=<%= t.getBno() %>";
		}
		
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
		
		
		document.querySelector( '#updateBtn' ).addEventListener( 'click', () => {
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
		
		// 사진 옆으로 넘겨서 보기
		$('#titleImgArea').on('click', function(){
			$('#thumbImg1').click();
		});
		
		$('#contentImgArea1').on('click',function(){
			$('#thumbImg2').click();
		});
		
		$('#contentImgArea2').on('click',function(){ 
			$('#thumbImg3').click();
		});
		
		$('#contentImgArea3').on('click',function(){ 
			$('#thumbImg4').click();
		});
		
		$('#fileArea').hide();
		
		// 사진 미리보기 구현
		function loadImg(img, num) {
			if(img.files && img.files[0]) {
				var reader = new FileReader();
				
				reader.onload = function(e){
					switch(num) {
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
</html>