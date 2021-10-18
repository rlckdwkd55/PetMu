package com.kh.petmu.like.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kh.petmu.like.model.service.likeService;

/**
 * Servlet implementation class likeUpdate
 */
@WebServlet("/likeUpdate.do")
public class likeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String userId = request.getParameter("id");
		
		//System.out.println("서블릿 체크 : " + bno + userId);
		
		likeService service = new likeService();
		
		// 해당 게시글에 좋아요를 눌렀는지 db로 확인
		int result1 = service.selectLike(bno, userId);
		
		int like = 0;
		String text = null;
		
		if(result1 == 0) { // 좋아요를 누른적이 없으면
			// bno의 like 값 + 1
			int result2 = service.updateLike(bno);
			
			// 좋아요 개수 가져오기
			like = service.getLikeCount(bno);
			
			// 좋아요 테이블 생성
			if(result2 > 0) {
				int result3 = service.insertLike(bno, userId);
				
				if(result3 > 0) {
					
					// 좋아요 개수와 실행결과 보내주기
					
					JSONObject obj = new JSONObject();

					obj.put("like", like);
					obj.put("result", 1);
					
					response.setContentType("application/json; charset=UTF-8");
					response.getWriter().print(obj.toJSONString());
					
				}
			}
		} else {
			text = "이미 게시글을 추천하였습니다.";
			
			JSONObject obj = new JSONObject();
			
			obj.put("text", text);
			obj.put("result", 0);
			
			response.setContentType("application/json; charset=UTF-8" );
			response.getWriter().print(obj.toJSONString());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
