<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cateNo = (int)request.getAttribute("cate");
%>    
    
	<div class="sideMenu">
	    <div id="sideArea">
	        <ul>
	            <li class="listTitle"> 동물병원 / 약국
	                <ul>
		                <%if(cateNo == 3) {%>
		                    <li style="border-bottom: solid 3px orange; ">
		                    	<a href="<%= request.getContextPath()%>/selectList.bo?cate=3" style="font-weight: bold; text-decoration : none;">
		                    		병원 / 약국 정보 공유
		                    	</a>
		                    </li>
		                    <br />
		            	<%} else {%>
		            		<li class="nonFocus"><a href="<%= request.getContextPath()%>/selectList.bo?cate=3">병원 / 약국 정보 공유</a></li>
		                    <br />
		            	<%}%>
		                    <%if(cateNo == 4) {%>
		                    <li style="border-bottom: solid 3px orange; ">
		                    	<a href="<%= request.getContextPath()%>/selectList.bo?cate=4" style="font-weight: bold; text-decoration : none;">
		                    		이용 후기
		                    	</a>
		                    </li>
		                    <br />
		            	<%} else {%>
		            		<li class="nonFocus"><a href="<%= request.getContextPath()%>/selectList.bo?cate=4">이용 후기</a></li>
		                    <br />
		            	<%}%>
	                </ul>
	            </li>
	            <li class="listTitle"> 동반입장 장소
	                <ul>
	                    <%if(cateNo == 5) {%>
	                    <li style="border-bottom: solid 3px orange; ">
	                    	<a href="<%= request.getContextPath()%>/selectList.bo?cate=5" style="font-weight: bold; text-decoration : none;">
	                    		장소 공유
	                    	</a>
	                    </li>
	                    <br />
	            		<%} else {%>
	            		<li class="nonFocus"><a href="<%= request.getContextPath()%>/selectList.bo?cate=5">장소 공유</a></li>
	                    <br />
	            		<%}%>
	                    <%if(cateNo == 6) {%>
	                    <li style="border-bottom: solid 3px orange; ">
	                    	<a href="<%= request.getContextPath()%>/selectList.bo?cate=6" style="font-weight: bold; text-decoration : none;">
	                    		이용 후기
	                    	</a>
	                    </li>
	                    <br />
	            		<%} else {%>
	            		<li class="nonFocus"><a href="<%= request.getContextPath()%>/selectList.bo?cate=6">이용 후기</a></li>
	                    <br />
	            		<%}%>
	                </ul>
	            </li>
	            <li class="listTitle"> 기타 장소
	                <ul>
	                    <%if(cateNo == 7) {%>
	                    <li style="border-bottom: solid 3px orange; ">
	                    	<a href="<%= request.getContextPath()%>/selectList.bo?cate=7" style="font-weight: bold; text-decoration : none;">
	                    		장소 공유
	                    	</a>
	                    </li>
	                    <br />
	            		<%} else {%>
	            		<li class="nonFocus"><a href="<%= request.getContextPath()%>/selectList.bo?cate=7">장소 공유</a></li>
	                    <br />
	            		<%}%>
	                    <%if(cateNo == 8) {%>
	                    <li style="border-bottom: solid 3px orange; ">
	                    	<a href="<%= request.getContextPath()%>/selectList.bo?cate=8" style="font-weight: bold; text-decoration : none;">
	                    		이용 후기
	                    	</a>
	                    </li>
	                    <br />
	            		<%} else {%>
	            		<li class="nonFocus"><a href="<%= request.getContextPath()%>/selectList.bo?cate=8">이용 후기</a></li>
	                    <br />
	            		<%}%>
	                </ul>
	            </li>
	        </ul>
	    </div>
	</div>
	
	
	
	

	
	
	
	
	
	
	
	
	