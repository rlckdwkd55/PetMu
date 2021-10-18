package com.kh.petmu.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.board.model.service.BoardService;
import com.kh.petmu.board.model.vo.Board;

/**
 * Servlet implementation class BoardUpdateView
 */
@WebServlet("/updateView.bo")
public class BoardUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		// 해당 번호의 게시글 찾아오기
		Board b = new BoardService().updateView(bno);
		
		String page = "";
		if(b != null) {
			request.setAttribute("board", b);
			request.setAttribute("cate", cateNo);
			switch(cateNo) {
				case 3 : page = "views/board/info_review/hospital/infoUpdate.jsp";
						 break;
				case 4 : page = "views/board/info_review/hospital/reviewUpdate.jsp";
						 break;
				case 5 : page = "views/board/info_review/place/infoUpdate.jsp";
				 		 break;
				case 6 : page = "views/board/info_review/place/reviewUpdate.jsp";
						 break;
				case 7 : page = "views/board/info_review/etc/infoUpdate.jsp";
				 		 break;
				case 8 : page = "views/board/info_review/etc/reviewUpdate.jsp";
						 break;
				}
			
		} else {
			request.setAttribute("error-msg", "게시글 작성 화면 로드 실패");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
