package com.kh.petmu.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/delete.bo")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		int result = new BoardService().deleteBoard(bno);
		
		String page = "";
		
		if(result > 0) {
			request.setAttribute("cate", cateNo);
			
			// 각 카테고리별 리스트로 이동
				switch(cateNo) {
				case 3 : page = "selectList.bo?cate=3";
						 break;
				case 4 : page = "selectList.bo?cate=4";
						 break;
				case 5 : page = "selectList.bo?cate=5";
				 		 break;
				case 6 : page = "selectList.bo?cate=6";
						 break;
				case 7 : page = "selectList.bo?cate=7";
				 		 break;
				case 8 : page = "selectList.bo?cate=8";
						 break;		 
						 
				}
			
			response.sendRedirect(page);
		} else {
			request.setAttribute("error-msg", "게시글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
