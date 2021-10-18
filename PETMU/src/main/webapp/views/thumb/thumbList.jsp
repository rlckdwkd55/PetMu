<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.petmu.thumb.model.vo.*" %>
<%
	ArrayList<Thumbnail> list = (ArrayList<Thumbnail>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : 댕냥자랑</title>
<script src="<%= request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<!-- CSS 적용 -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/thumbList.css" />
<!-- 타이틀로고 -->
<link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath() %>/resources/images/petmu.ico" /> 
<!-- fontawesome 아이콘cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" /> 
<!-- 구글폰트 cdn -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<body>

	<%@ include file="../common/header.jsp" %>

	<section>
		<br>
		<div id="Bhead">
		<i id="cateIcon" class="far fa-images"></i>
		<label class="category"> 나의 댕냥 자랑</label>
		<p id="photoInfo">
			반려동물의 모습을 사진으로 자랑하세요!
		</p>
		</div>
		
		<div align="right" style="width: 1340px; padding-bottom: 5px;">
			<select name="sortList" id="sortList">
				<option value="">:::정렬:::</option>
				<option ${(param.sort == "new") ? "selected" : " " } value="new">최신순</option>
				<option ${(param.sort == "cmt") ? "selected" : " " } value="cmt">댓글순</option>
				<option ${(param.sort == "like") ? "selected" : " " } value="like">추천순</option>
			</select>
		</div>
		
		<hr />
		<div id="thumbnailArea">
			<% for(Thumbnail thumb : list) { %>
				<div class="thumb-list" align="center">
					<div>
						<input type="hidden" name="bno" value="<%= thumb.getBno() %>" />
						<img src="<%=request.getContextPath() %>/resources/thumbFiles/<%= thumb.getBfile() %>"
						     width="230px" height="200px"/>					
					</div>
					
					<ul>
						<li id="btitle"><%= thumb.getbtitle() %></li>

						<li><%= thumb.getbwriterNick() %> (<%= thumb.getbwriterId() %>)</li>
						<li>
		                     <i style="color:darkgrey" class="far fa-eye"></i> <%= thumb.getBcount() %>&nbsp;&nbsp;&nbsp;
		                     <i style="color:orange;" class="fas fa-thumbs-up"></i> <%= thumb.getlikeCount() %>&nbsp;&nbsp;&nbsp;
		                     <i style="color:darkgrey;" class="far fa-comment-dots"></i> <%= thumb.getCcount() %>   
                 		</li>

					</ul>
				</div>
							
			<% } %>
			<br><br>
			
			<% if ( m != null ) { %> 
			<button id="btn1" onclick="location.href='views/thumb/thumbInsert.jsp'">작성하기</button>

			<% } %> 
		</div>
		
		<br /><br /><br />
		
		<%-- 페이지 처리 코드 넣기 --%>
		
		<div class="pagingArea" align="center">
		
		<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn?currentPage=1'"><i class="fas fa-angle-double-left"></i></button>
			<%  if(currentPage <= 1){  %>
			<button disabled><i class="fas fa-angle-left"></i></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn?currentPage=<%=currentPage - 1 %>'"><i class="fas fa-angle-left"></i></button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled style="color : orange;"><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn?currentPage=<%= p %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled><i class="fas fa-angle-right"></i></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn?currentPage=<%=currentPage + 1 %>'"><i class="fas fa-angle-right"></i></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/selectList.tn?currentPage=<%= maxPage %>'"><i class="fas fa-angle-double-right"></i></button>
		
		</div>
		 
		<br /><br /><br />
		
		<div id="searchArea">
			<div id="searchFrm">
				<select id="searchTarget" name="searchTarget">
				<%-- 삼항연산자 사용
						옵션(searchTarget)에서 value(title, writerId, writerNick)를 
						선택한게 맞다면 선택한 옵션이 남아있게 함.
						검색버튼을 누르고 페이지가 바뀌어도 선택한 옵션이 그대로 선택되어 있음.
				 --%>
					<option ${(param.searchTarget == "title") ? "selected" : " " } value="title">제목</option>
					<option ${(param.searchTarget == "writerId") ? "selected" : " " } value="writerId">아이디</option>
					<option ${(param.searchTarget == "writerNick") ? "selected" : " " } value="writerNick">닉네임</option>
				</select> &nbsp; 
					<input id="bsearch" type="text" name="bsearch" value="${param.keyword}"
							placeholder="검색어를 입력해주세요" /> &nbsp; 
					<input id="searchBtn" type="button" value="검색" onclick="search();" />
			</div>
		</div>
	</section>
	
	<br />
	<%@ include file="../common/footer.jsp" %>

</body>
<script>
//작성하기 버튼 함수
$(function(){
	$('.thumb-list').click(function(){
		// var bno = $(this).children().children().eq(0).val();
		var bno = $(this).find('input').val();
		// console.log(bno);
		
		location.href = '<%= request.getContextPath()%>/selectOne.tn?bno=' + bno;
	});
})

// 검색 기능
function search(){
	var target = $('#searchTarget').val();
	var keyword = $('#bsearch').val();
	
	console.log(keyword);
	
	if(!keyword){
		alert("검색어를 입력해주세요.")
	} else {
		location.href = "<%= request.getContextPath()%>/selectList.tn?searchTarget=" + target + "&keyword=" + keyword;
	}
}

// 게시글 정렬
$('#sortList').on('change', function(){
	var sortL = $(this).val();
	
	switch(sortL){
	
	case "new" : location.href = "<%= request.getContextPath()%>/selectList.tn";
				 break;
	case "cmt" : location.href = "<%= request.getContextPath()%>/selectSortList.tn?sort=" + sortL;
				   break;
	case "like" : location.href = "<%= request.getContextPath()%>/selectSortList.tn?&sort=" + sortL;
	   			   break;
	}
	
});
</script>
</html>