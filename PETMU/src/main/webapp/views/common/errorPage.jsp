<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("error-msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PETMU : ERROR</title>
    <!-- fontawesome 아이콘cdn -->
    <link rel="stylesheet" 
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" /> 
    <style>
        section {
            margin-top: 200px;
            width : 1300px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }      

        h1 {
            font-size : 40pt;
            
        }
        

        i {
            color : red;
            
        }

        .errormsg {
            font-size : 20px;
            margin-top: -20px;
            text-align: center;
        }

        #goMainBtn {
            width : 150px;
            height : 50px;
            display: block;
            margin: auto;
            border: 1px solid darkgrey;
            /* border-radius: 3px; */
            /* font-weight: bold; */
            font-size: 17px;
            background: white;
        }

        #goMainBtn:hover {
            background: darkgrey;
            color : white;
            cursor : pointer;
        }
        
    </style>
    </head>
<body>
    <section>
        <h1><i class="fas fa-exclamation-triangle"></i> ERROR <br>
            <sub>:: <%= msg %></sub></h1>
        <div class="errormsg">
            서비스 수행 중 에러가 발생하였습니다. <br>
            해당화면이 계속 보인다면 관리자에게 문의해주세요.
        </div>
        <br><br>
        <button id="goMainBtn" onclick="goMain();">메인으로</button>

    </section>
    
    <script>
        function goMain(){
        	location.href = "<%= request.getContextPath()%>/index.jsp";
        }
    </script>
</body>
</html>