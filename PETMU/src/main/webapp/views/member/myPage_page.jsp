<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.petmu.board.model.vo.*, java.util.*"%>
    
 
 <%
 	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
 	PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
 	
 	int startPage = pi.getStartPage();
 	int endPage = pi.getEndPage();
 	int maxPage = pi.getMaxPage();
 	int currentPage = pi.getCurrentPage();
 	int listCount = pi.getListCount();
 %>
 
 
<!DOCTYPE html>
<html>
<head>
    <title>PETMU : 마이페이지</title>
    <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/c10cbac54f.js" crossorigin="anonymous"></script>

    <style>

#td{
font-size: 20px;
padding-right: 300px;

}

#tr{

    height: 50px;

}

#btn{


    color: coral;
    font-size: 20px;
    font-weight: 600;


}


#listArea tbody td:nth-child(3) {
        text-align: left;
    }
	
	#listArea {
		border-collapse : collapse;
	}
	
    #listArea th {
    	height : 30px;
        border-top : 3px solid gray;
        border-bottom : 1px solid lightgray;
    }
    
   	#listArea td {
   		height : 30px;
        border-bottom : 1px solid lightgray;
    }
    
    #listArea td:hover {
    	cursor:pointer;
    }

    </style>

</head>
<header>
    <%@ include file="/views/common/header.jsp" %>
    </header> 
<body style="margin-left: 150px; margin-right: 150px; background-color:#f5f6f7;">
    

  <br><br>
    <h1> <label class="fas fa-user-alt" style="color: coral;"  ></label> 마이페이지</h1>
  <hr style= "border-width: 1px; ">

 
<br>
            
<div style="display:inline-block;">
            <table align="left" style="display : block;">
<br><br>
                <tr>

                    <td><h1 style= "color: coral;"><%= m.getNickname() %></h1></td>

                    <td><a href="<%= request.getContextPath()%>/views/member/nicknameUpdate_page.jsp"><label class="far fa-edit" style="color: coral; font-size: 30px; cursor: pointer;" ></label></a></td>
                </tr>

       

                <tr id="tr">

                    <td id="td" style="font-weight: 600;" >아이디&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td id="td" style="color: gray;" ><%= m.getUserId() %></td>

                </tr>


                <tr id="tr">

                    <td id="td" style="font-weight: 600;">이름</td>
                    <td id="td"  style="color: gray;" ><%= m.getUserName() %></td>
                    

                </tr>

                <tr id="tr">

                    <td id="td" style="font-weight: 600;">생년월일</td>
                    <td id="td" style="color: gray;" > <%= m.getBirth() %></td>

                </tr>

                <tr id="tr">

                    <td id="td" style="font-weight: 600;">E-Mail</td>
                    <td id="td" style="color: gray;" ><%= m.getEmail() %></td>

                </tr>

       

                <tr id="tr">

                    <td id="td" style="font-weight: 600;">주소</td>
                    <td id="td" style="color: gray; width : 400px" " ><%= m.getAddress() %></td>

                </tr>
                
                <tr id="tr">
                
                <%
               
                String pet;
                if (m.getPetName() == null) {
	            pet = "없음"; }

                     else {
	         pet = m.getPetName();
	
            }
	
	
               %>

                    <td id="td" style="font-weight: 600;">반려동물 정보</td>
                    <td id="td" style="color: gray;" >(<%= m.getPetType() %>) |  <%=pet %> </td>

                </tr>
            </table>
        </div>

<div>
<table align="right" style="margin-top: 50px;">    
    <tr>
    <td id="btn"><a href="<%= request.getContextPath()%>/views/member/userUpdate_page.jsp" style="color: coral; text-decoration-line: none; cursor: pointer;" >개인정보수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td id="btn"><a href="<%= request.getContextPath()%>/views/member/withdraw_page.jsp" style="color: coral; text-decoration-line: none; cursor: pointer;" >회원탈퇴</a>
</tr>
</table>
</div>

      

            <div id="boardTitle" style="margin-top: 100px;">
                <hr>
                <h2 >내가 작성한 게시글</h2>
            </div>
    
            <div class="tableArea">
                <table id="listArea" align="center">
                    <thead>
                        <tr>
                            <th width="50px">No.</th>
                            <th width="700px">제목</th>
                            <th width="500px">카테고리</th>
                            <th width="200px">작성일</th>
                            <th width="200px">조회수</th>
                            <th width="200px">추천수</th>
                        </tr>
                    </thead>
                    <tbody align="center">
                     <%-- for each문을 통해 board list 담아주기 --%>

                        <%for(Board b : list) {%>
                        <tr>
							<input type="hidden" id="Bno" value="<%=b.getBno() %>" /> <%--bno 받아오기 --%>
							<input type="hidden" id="CateNo" value="<%=b.getCateNo() %>" /> <%--bno 받아오기 --%>
                            <td style="text-align:center"><%=b.getBno() %></td> <%-- bno 받아오기 --%>
                            <td><%=b.getBtitle() %></td> <%--btitle 받아오기 --%>
                            <td align = "center"><%String cateName = "";
                                  switch(b.getCateNo()) {
                                  
                                  case 1 : cateName="자유 게시판";
                                  break;
                                  
                                  case 2 : cateName="사진 게시판";
                                  break;
                                  
                                  case 3 : cateName="병원/약국 정보 게시판";
                                  break;
                                  
                                  case 4 : cateName="병원/약국 후기 게시판";
                                  break;
                                  
                                  case 5 : cateName="동반입장 장소 정보 게시판";
                                  break;
                                  
                                  case 6 : cateName="동반입장 장소 후기 게시판";
                                  break;
                                  
                                  case 7 : cateName="기타 장소 정보 게시판";
                                  break;
                                  
                                  case 8 : cateName="기타 장소 후기 게시판";
                                  break;
                                  
                                  }
                            
                            %><%=cateName%></td> <%-- cateNo 받아오기 --%>
                            <td><%=b.getBdate() %></td> <%--uploadDate 받아오기 --%>
                            <td><%=b.getBcount()%></td> <%-- likeCount 받아오기 --%>
                            <td><i class="fas fa-thumbs-up"></i> + <%=b.getLikeCount() %></td>                        
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>

            <br><br><br>
</body>
<footer style="padding-top : 150px">  <%@ include file="/views/common/footer.jsp" %>	</footer>
<script>

$('#listArea td').mouseenter(function(){
    $(this).parent().css({"background" : "#f2f2f2", "cursor" : "pointer"});
 }).mouseout(function(){
    $(this).parent().css({"background" : "white"});
 }).on('click', function(){
     var bno = $(this).parent().find('input[id=Bno]').val();
     var cateNo = $(this).parent().find('input[id=CateNo]').val();
     console.log(bno);
     
     if(cateNo >= 3) {
     
     location.href = "<%= request.getContextPath() %>/selectOne.bo?cate="+ cateNo +"&bno=" + bno;
     
     } else if (cateNo == 1) {
    	 

         location.href = "<%= request.getContextPath() %>/selectOne.fb?&bno=" + bno;
    	 
     } else if (cateNo == 2) {
    	 

    	  location.href = "<%= request.getContextPath() %>/selectOne.tn?&bno=" + bno;
    	 
    	 
     }
     
  });

</script>
</html>>