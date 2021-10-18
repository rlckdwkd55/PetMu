<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.petmu.freeBoard.model.vo.*, com.kh.petmu.comment.model.vo.*, java.util.*"%>

<%
	freeBoard fb = (freeBoard) request.getAttribute("freeBoard");
	ArrayList<Comment> coList = (ArrayList<Comment>)request.getAttribute("coList");
	int cmtCount = (int)request.getAttribute("cmtCount");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : 자유게시판</title>
<script src="<%= request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<!-- CSS 적용 -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/FboardDetail.css" />
<!-- 타이틀 로고 -->
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath()%>/resources/images/petmu.ico" />
<!-- awesome 아이콘 cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" />
<!-- 구글폰트 cdn -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">

</head>
<body>

	<%@ include file="../common/header.jsp"%>

	<div class="outer">
		<br> <br>
		<div class="category">
			<i id="cateIcon" class="far fa-edit"></i> 
			<a id="atag" href='<%=request.getContextPath()%>/selectList.fb'>자유게시판</a><br>
			<br>
		</div>

		<div class="tableArea">
			<table style="width: 1300px;">
				<tr>
					<th id="btitle" name="btitle" colspan="1"><%=fb.getbtitle()%></th>
                    <input type="hidden" name="bno" value="<%=fb.getBno() %>" />
					<th colspan="3"><%=fb.getBdate()%></th>
				</tr>
				<tr>
					<td ><%= fb.getbwriterNick() %><span> (<%= fb.getbwriterId() %>)</span></td>
					<td width="100px" align="right">조회수 <%= fb.getBcount()%></td>
					<td width="100px" align="right">추천수 <%= fb.getlikeCount() %></td>
					<td width="90px" align="right">댓글 <%= cmtCount %></td>
				</tr>
			</table>
			
			<br>
			<p>
				<%--bcontent 가져오기 --%>
				<%=fb.getBcontent()%>
			</p>
			
			<br /><br />

			<div id="likeArea" align="center">
				<span id="likeBtn"> <i class="fas fa-thumbs-up"></i> 
					<span class="likeCnt"><%=fb.getlikeCount()%> Like</span> <%--likecount 가져오기 --%>
				</span>
			</div>
			<br />
			
			<%
			if (fb.getBfile() != null && fb.getBfile().length() > 0) {
			%>
		<label>첨부파일 </label><a href="<%=request.getContextPath()%>/resources/boardUploadFiles/<%=fb.getBfile() %>" download>
							<%=fb.getBfile() %></a>
		<% } %>

			<span id="btnArea">
				<% if (m != null && m.getUserId().equals(fb.getbwriterId())) {	%>
				<button id="updateBtn"
					onclick="location.href='<%=request.getContextPath()%>/updateView.fb?bno='+<%=fb.getBno()%>">수정</button>
				<button id="deleteBtn" onclick="deleteOk();">삭제</button>
				<%	} %>
			</span>
			<br />
		
		<br />
		<hr />
			<div class="cmtArea">
				<div id="cmtCount">
					댓글 <label><%=cmtCount%></label>
					<%--cmtCount 가져오기 --%>
				</div>

				<div id="cmtInsert">
					<textarea name="ccontent" id="ccontent" cols="166" rows="3"
						style="resize: none;" placeholder="댓글을 남겨보세요."></textarea>
					<button name="cmtBtn" id="cmtBtn" onclick="postCmt();">등록</button>
				</div>

				<div class="cmtListArea">
					<%--for each문으로 comment정보 담기 --%>
					<%
					for (Comment c : coList) {
					%>
					<div class="cmts<%=c.getCno()%>">
						<div class="cmtHead">
							<div>
								<input type="hidden" name="cno" value="<%=c.getCno()%>" /> <%--cno 가져오기 --%>
								<span style="font-size: 14px; font-weight: bold;"><%=c.getCwriterNick()%></span>
								<input type="hidden" name="cwriterId" value="<%=c.getCwriterId()%>" />
							</div>
							
							<%--usernickname과 cwriter 일치하면 보이기 --%>
							<%if(m != null && m.getUserId().equals(c.getCwriterId())) { %>
							<span style="font-size: 13px;"> 
							<i class="far fa-edit" id="updateCmt" onclick="updateCon('<%=c.getCno()%>', '<%=c.getCwriterNick()%>', '<%=c.getCcontent()%>');"></i>
					 &nbsp; <i class="far fa-trash-alt" id="deleteCmt"></i>
							</span>
							<%} %>
						</div>
						<br />
						<div class="cmtBody" style="font-size: 14px;">
							<p id="cmtContent<%=c.getCno()%>" , style="margin: 0px;"><%=c.getCcontent()%></p>
						</div>
						<div class="cmtFoot"><%=c.getCdate()%></div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>


			<br /> <br />

			<div align="center" class="btnArea2">
				<button id="goMenuBtn"
					onclick="location.href='<%=request.getContextPath()%>/selectList.fb'">목록으로</button>

			</div>
			</div>

			<br /> <br />



		<%@ include file="../common/footer.jsp"%>
</body>
<%if(m != null) { %>
	<script>
	// 댓글 입력 함수
	
		function postCmt(){
			<%-- locatoin.href="<%= request.getContextPath() %>/cmtInsert.co"; --%>
			// jsondata 형식으로 값을 담아 ajax로 보내기
			var jsonData = {
					bno : <%=fb.getBno() %>,
					cwriterId : "<%= m.getUserId() %>", /* m.getUserId */
					cwriterNick : "<%= m.getNickname()%>", /* m.getnickname */
					ccontent : $('#ccontent').val()
				}
				
				
				if(!$('#ccontent').val()){
					// 입력창에 내용이 없으면
					alert("댓글을 입력해주세요.");
					// 내용이 있다면
				} else {
					$.ajax({
						url : "<%= request.getContextPath() %>/cmtInsert.co",
						type : "post",
						data : jsonData,
						success : function(data){
							if(data == 1){ // 데이터 처리가 성공적으로 완료되면 페이지 새로고침
								document.location.reload();
							}
						}, error : function(error){
							alert("댓글 등록 실패");
						}
						
					});
				}
		}
	
		// 게시글 좋아요 기능 함수
		$('#likeBtn').click(function(){
			
			var bno = <%= fb.getBno() %>;
			var id = '<%= m.getUserId() %>'; <%-- <%= m.getUserId() %> --%> 
			
			console.log(bno, id);
			
			if(!confirm("이 게시글을 추천 하시겠습니까?")){
				// 취소 버튼 클릭시
			} else {
				/// 확인 버튼 클릭시
				$.ajax({
					url : "<%= request.getContextPath() %>/likeUpdate.do",
					type : "post",
					data : { bno, id },
					success : function(data){
						if(data.result == 1){
							$('.likeCnt').html(data.like);
						} else {
							alert(data.text);
						}
					}, error : function(error){
						alert("게시글 추천 실패");
					}
				});
			}
			
			
		});

	</script>
<%} else {%>
	<script>
		function postCmt(){
			alert("로그인 후 댓글을 작성해 주세요.");
		}
		
		$('#likeBtn').click(function(){
			alert("로그인 후 추천이 가능합니다.");
		});
	</script>
<%} %>

<script>

// 수정 아이콘 클릭시 수정 댓글창으로 변경
function updateCon(cno, nick, content){
	var htmls = "";
	
	htmls += '<div class="cmtUpdate">';
	htmls += '<div class="upHead">';
	htmls += '<div>';
	htmls += '<input type="hidden" id="upCno" value="' + cno + '"/> ';
	htmls += '<span style="font-size: 14px; font-weight: bold;">';
	htmls += nick;
	htmls += '</span>';
	htmls += '</div>';
	htmls += '<span style="font-size: 13px;">';
	htmls += '<button id="upBtn" onclick="sendCon();")>수정</button> &nbsp;';
	htmls += '<button id="delBtn" onclick="document.location.reload();">취소</button>';
	htmls += '</span>';
	htmls += '</div>';
	htmls += '<br>';
	htmls += '<div class="upBody" style="font-size: 14px;">';
	htmls += '<textarea name="upCon" id="upCon" cols="180" rows="3" style="resize: none;">';
	htmls += content;
	htmls += '</textarea>';
	htmls += '</div>';
	htmls += '<div class="upFoot">2021-10-08</div>';
	htmls += '</div>';
	
	$('.cmts'+cno).replaceWith(htmls);
	$('#upCon').focus();
}
// 댓글 수정 함수
function sendCon() {
	var jsonData = {
			cno : $('#upCno').val(),
			ccontent : $('#upCon').val()
		}
		
		//console.log(jsonData); // 전송할 데이터 확인
		if(!$('#upCon').val()){
			alert("댓글을 입력해주세요.");
		} else {
			$.ajax({
				url : "<%= request.getContextPath() %>/cmtUpdate.co",
				type : "post",
				data : jsonData,
				success : function(data){
					if(data == 1){ // 데이터 처리가 성공적으로 완료되면 페이지 새로고침
						document.location.reload();
					}
				}, error : function(error){
					alert("댓글 등록 실패");
				}
				
			});
		}
}
		

// 댓글 삭제 함수
	$('i[id*=deleteCmt]').click(
		function (){
			if (!confirm("삭제시 복구 할 수 없습니다. 삭제하시겠습니까?")) {
		        // 취소(아니오) 버튼 클릭 시 이벤트
		    } else {
		        // 확인(예) 버튼 클릭 시 이벤트
		        
		        // 가장 가까운 div의 cno값
		        var cno = $(this).closest('div').find('input[name=cno]').val();
		        var bno = <%= fb.getBno() %>;
		        
		    	$.ajax({
					url : "<%= request.getContextPath() %>/cmtDelete.co",
					type : "post",
					data : { cno, bno },
					success : function(data){
						if(data == 1){ // 데이터 처리가 성공적으로 완료되면 페이지 새로고침
							document.location.reload();
						}
					}, error : function(error){
						alert("댓글 삭제 실패");
					}
					
				});        
		    }
		}
	);

// 게시글 삭제 함수
function deleteOk(){
 	if (!confirm("삭제시 복구 할 수 없습니다. 삭제하시겠습니까?")) {
        // 취소(아니오) 버튼 클릭 시 이벤트
    } else {
        // 확인(예) 버튼 클릭 시 이벤트
    	location.href='<%=request.getContextPath()%>/delete.fb?bno=<%=fb.getBno() %>';         
    }
	
}
</script>

</html>