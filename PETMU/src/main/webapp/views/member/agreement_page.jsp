<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>PETMU : 회원가입</title>
    <link rel="petmu icon" href="<%=request.getContextPath()%>/resources/images/petmu.ico">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body style="min-width: 1000px; background-color:#f5f6f7">


    <div style=" position: absolute; top: 5%; left: 50%; transform: translateX(-50%); " >
        <h2 align="center" style="margin-top : 150px"><a href="../../index.jsp"> <img width="250" height="100" src="<%=request.getContextPath()%>/resources/images/petmu.png" alt="펫뮤" style="" ></a> <br><br> 회원 약관</h2>
        <table align="center" class="member_join_step03_terms_table" >
            <tr>
                <td colspan="2" style="padding-bottom:10px;" class="txtl">
                    <input class="check-all" type="checkbox" name="agree_all" id="agree_all" value="Y" />
                    <label for="join_agree_all"
                        style="padding-left:7px;color:#000000;font-size:14px;word-break:keep-all">아래 내용에 모두
                        동의합니다.</label>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="border-top:1px solid #b0b6b8;padding-bottom:10px;"></td>
            </tr>
            <tr>

                <td class="txtr">
                </td>
            </tr>
            <tr>
                <td class="txtl">
                    <input class="agree" type="checkbox" name="agree1" id="agree1" value="Y" />
                    <label for="join_agree1" style="padding-left:7px;">이용약관(필수)
                </td>
                <td class="txtr">
                    <a href="agree1.jsp" target="_blank">
                        <span class="terms_view_btn">내용보기</span>
                    </a>
                </td>
            </tr>

            <tr>
                <td class="txtl" style="padding-bottom:10px;">
                    <input class="agree" type="checkbox" name="agree2" id="agree2" value="Y" />
                    <label for="join_agree2" style="padding-left:7px;">개인 정보 수집 및 이용 동의(필수)</label>

                </td>
                <td class="txtr" style="padding-bottom:10px;">
                    <a href="agree2.jsp" target="_blank">
                        <span class="terms_view_btn">내용보기</span>
                    </a>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="border-top:1px solid #b0b6b8; text-align: left; line-height:18px; padding-top:10px; padding-bottom: 50px;">
                    <span style="display: flex;">고객님께서는 Petmu 회원 서비스 이용에 필요한 <br> 최소한의 개인정보 수집ㆍ이용에 동의하지 않을 권리가 있으며, <br> 거부 시에는 Petmu 서비스 이용이
                        제한됩니다.</span>
                </td>
            </tr>
        </table>

        <div style= "display: flex;">
          

            <tr>
                <td colspan="2" style="padding:50px 0 24px 0; padding-left: 100px;">
                    <span class="c_btn" id="c_btn"
                        style="padding: 15px 30px 15px 30px; margin-right: 10px; font-size: 15px; color: #000; background-color: #fff;padding: 15px 78px 15px 78px; font-weight: 900;border-radius: 2px;border: 1px solid #cdcdcd; cursor: pointer;">동의하고
                        회원가입</span>
                    <span class="b_btn" id = "b_btn"
                        style="font-size: 15px; color: #000; background-color: #fff;padding: 15px 78px 15px 78px; font-weight: 900;border-radius: 2px;border: 1px solid #cdcdcd; cursor: pointer;">취소</span>
                </td>
               
        </div>


    </div>


</body>
<div  style="position: absolute; top: 85%; left: 50%; transform: translateX(-50%);">
<footer>  <%@ include file="/views/common/footer.jsp" %>	</footer>
</div>

    <script>
        $('.check-all').click(function () {

            if($('.check-all').is(":checked") ==true && $('#agree1').is(":checked")==true && $('#agree2').is(":checked")==true){

                $('.check-all').is(":checked") == false;
                $('#agree1').is(":checked") == false;
                $('#agree2').is(":checked") == false;

             } else if ($('.check-all').is(":checked") ==true && $('#agree1').is(":checked")==true && $('#agree2').is(":checked")==false)
            {
                $('#agree2').click();



            }else if ($('.check-all').is(":checked") ==true && $('#agree1').is(":checked")==false && $('#agree2').is(":checked")==true)
            {
                $('#agree1').click();



            }else if ($('.check-all').is(":checked") ==false && $('#agree1').is(":checked")==false && $('#agree2').is(":checked")==true)
            {
                $('#agree2').click();



            } else if ($('.check-all').is(":checked") ==false && $('#agree1').is(":checked")==true && $('#agree2').is(":checked")==false)
            {
                $('#agree1').click();

            }else
            {
            $('.agree').click();
        }
        });

     



        
		$("#c_btn").click(function(){
			if($("#agree1").is(":checked") == false){
				alert("이용약관(필수)에 동의 하여야 다음 단계로 진행이 가능합니다.");
				return;
			}else if($("#agree2").is(":checked") == false){
				alert("개인정보 수집 및 이용동의(필수)에 동의 하여야 다음 단계로 진행이 가능합니다.");
				return;
			}else{
				$(location).attr("href","sign_in_page.jsp");
			}
		});

        $("#b_btn").click(function(){
			
				$(location).attr("href","../../index.jsp");
			});


   
    </script>



</html>