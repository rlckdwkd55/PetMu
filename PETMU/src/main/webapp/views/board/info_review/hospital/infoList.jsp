<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.petmu.board.model.vo.*, java.util.*"%>
<%--vo임포트하고 Board list, PageInfo 받아오기 --%>
 
 <%
 	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
 	PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
 	
 	int startPage = pi.getStartPage();
 	int endPage = pi.getEndPage();
 	int maxPage = pi.getMaxPage();
 	int currentPage = pi.getCurrentPage();
 	int listCount = pi.getListCount();
 	int limit = pi.getLimit();
 %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PETMU : 병원 / 약국 정보</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sideMenu.css" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">
	<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/header.css" />  
	<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/boardList.css" /> 
    <script src="<%= request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
    
    <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath()%>/resources/images/petmu.ico" /> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


</head>

<body>
    <%@ include file="/views/common/header.jsp" %>
    <div class="main">

	<%@ include file="/views/common/sideMenu.jsp" %>	

        <div class="boardArea">
            <div id="boardTitle" style="font-family: 'Jua', sans-serif; font-size : 30px; padding-bottom : 10px;">
           		<i class="far fa-edit"></i>
                <label style="color : orange;"> 병원 / 약국 정보 공유</label>
            </div>
    		
    		<div align="right" style="width:1162px; padding-bottom : 5px;">
    			<select name="sortList" id="sortList">
    				<option value="">:::정렬:::</option>
    				<option  ${(param.sort == "new") ? "selected" : " " }value="new">최신순</option>
    				<option  ${(param.sort == "cmt") ? "selected" : " " }value="cmt">댓글순</option>
    				<option  ${(param.sort == "like") ? "selected" : " " }value="like">추천순</option>
    			</select>
    		</div>
    		
            <div class="tableArea">
                <table id="listArea">
                    <thead>
                        <tr>
                            <th width="50px">No.</th>
                            <th width="600px">제목</th>
                            <th width="150px">글쓴이</th>
                            <th width="150px">작성일</th>
                            <th width="100px">조회수</th>
                            <th width="100px">추천수</th>
                        </tr>
                    </thead>
                    <tbody align="center">
                        <%-- for each문을 통해 board list 담아주기 --%>

                        <%for(Board b : list) {%>
                        <tr>
							<input type="hidden" value="<%=b.getBno() %>" /> <%--bno 받아오기 --%>
                            <td><%=b.getBno() %></td> <%-- bno 받아오기 --%>
                            <%if(b.getCcount() != 0) {%>
                            <td><%=b.getBtitle() %> <label style="color : tomato; font-weight: bold;">[<%=b.getCcount() %>]</label></td> <%--btitle 받아오기 --%>
                            <%} else { %>
                            <td><%=b.getBtitle() %></td> <%--btitle 받아오기 --%>
                            <%}%>
                            <td><%=b.getBwriterNick() %></td> <%--userNickname 받아오기 --%>
                            <td><%=b.getBdate() %></td> <%--uploadDate 받아오기 --%>
                            <td><%=b.getBcount()%></td> 
                            <td><i class="fas fa-thumbs-up"></i> + <%=b.getLikeCount() %></td>  <%-- likeCount 받아오기 --%>                       
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
    		<br />
    		<!-- 총 게시글 수가 페이지 최대 게시글 수 보다 크면 페이징 표시 -->
    		<%if(pi.getListCount() > limit) {%>
	            <div class="pagingArea" align="center">
	    			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=1&cate=3'"><<</button> <%--cate check --%>
	    			<%-- currentPage, stardPage, endPage, listCount받아와서 페이징 --%>
	    			<%if(currentPage <= 1) {%>
	    			<button disabled><</button>
	    			<%} else { %>
	    			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%=currentPage - 1%>&cate=3'"><</button>
	    			<%} %>
	    			<%for(int p = startPage; p <= endPage; p++){ 
	    				if(p ==currentPage){
	    			%>
	    				<button disabled style="color:orange;"><%=p %></button>
	    				<%} else { %>
	    				<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%=p%>&cate=3'"><%=p%></button>
	    				<%} %>
	    			<%} %>
	    			<%if(currentPage >= maxPage) {%>
	    			<button disabled>></button>
	    			<%} else { %>
	    			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%=currentPage + 1%>&cate=3'">></button>
	    			<%} %>
	    			<button onclick="location.href='<%= request.getContextPath() %>/selectList.bo?currentPage=<%=maxPage %>&cate=3'">>></button>
	            </div>
	   		<%}%>  	       
            
            <div id="btnArea" align="right">
            	<%--insert.bo로 이동 --%>
            	<%if(m != null) { %> 
            	<button onclick="post();" id="postBtn">글쓰기</button>
            	<%} %>
            </div>
        </div>

      	<div id="searchArea">
			<div id="searchFrm">
				<select id="searchTarget" name="searchTarget">
					<option ${(param.searchTarget == "title") ? "selected" : " " }value="title">제목</option>
					<option ${(param.searchTarget == "writerId") ? "selected" : " " } value="writerId">아이디</option>
					<option ${(param.searchTarget == "writerNick") ? "selected" : " " } value="writerNick">닉네임</option>
				</select>
				&nbsp;
				<input id="bsearch" type="text" name="bsearch" 
						value="${param.keyword}" placeholder="검색어를 입력해주세요"/>
				&nbsp;
				<input id="searchBtn" type="button" value="검색" onclick="search();"/>
			</div>
		</div>
		
    </div>
     <%@ include file="/views/common/footer.jsp" %>
     
    
</body>
    <script>
 
    	function post(){
	    	location.href = "<%= request.getContextPath() %>/insertView.bo?cate=3";
    	}
    	
    	<%-- 게시글 td누르면 bno값들고 selectOne.bo로 이동--%>
    	$('#listArea td').mouseenter(function(){
			$(this).parent().css({"background" : "#f2f2f2", "cursor" : "pointer"});
		}).mouseout(function(){
			$(this).parent().css({"background" : "white"});
		}).on('click', function(){
    		var bno = $(this).parent().find('input').val();
    		location.href = "<%= request.getContextPath() %>/selectOne.bo?cate=3&bno=" + bno; <%--cate check --%>
    	});
    	
    	function search(){
    		var target = $('#searchTarget').val();
    		var keyword = $('#bsearch').val();
    		
    		//console.log(keyword);
    		
    		if(!keyword){
    			alert("검색어를 입력해주세요.")
    		} else {
    			location.href = "<%= request.getContextPath()%>/selectList.bo?cate=3&searchTarget=" + target + "&keyword=" + keyword; <%--cate check --%>
    		}
    	}
    	
    	
    	// 게시글 정렬
    	$('#sortList').on('change', function(){
    		var sortL = $(this).val();
    		
    		switch(sortL){
    		
    		case "new" : location.href = "<%= request.getContextPath()%>/selectList.bo?cate=3";
    					 break;
    		case "like" : location.href = "<%= request.getContextPath()%>/selectSortList.bo?cate=3&sort=" + sortL;
						   break;
    		case "cmt" : location.href = "<%= request.getContextPath()%>/selectSortList.bo?cate=3&sort=" + sortL;
			   			   break;
    		}
    		
    	});
    	
    	
    </script>
</html>










