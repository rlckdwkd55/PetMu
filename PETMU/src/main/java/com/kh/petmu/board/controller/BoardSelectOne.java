package com.kh.petmu.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.board.model.service.BoardService;
import com.kh.petmu.board.model.vo.Board;
import com.kh.petmu.comment.model.vo.Comment;

/**
 * Servlet implementation class BoardSelectOne
 */
@WebServlet("/selectOne.bo")
public class BoardSelectOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSelectOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		
		BoardService service = new BoardService();
		
		// 해당 번호의 게시물 찾아오기
		Board b = service.selectBoard(bno);
		
		// 해당 번호의 게시물 댓글 리스트 받아오기
		ArrayList<Comment> coList = service.selectCoList(bno);
		
		// 댓글 총 수
		int cmtCount = service.getCmtCount(bno);
		
		String page = "";
		
		if(b != null) {
			request.setAttribute("board", b);
			request.setAttribute("coList", coList);
			request.setAttribute("cmtCount", cmtCount);
			request.setAttribute("cate", cateNo);
			
			switch(cateNo) {
				case 3 : page = "views/board/info_review/hospital/infoDetail.jsp";
						 break;
				case 4 : page = "views/board/info_review/hospital/reviewDetail.jsp";
						 break;
				case 5 : page = "views/board/info_review/place/infoDetail.jsp";
				 		 break;
				case 6 : page = "views/board/info_review/place/reviewDetail.jsp";
						 break;
				case 7 : page = "views/board/info_review/etc/infoDetail.jsp";
				 		 break;
				case 8 : page = "views/board/info_review/etc/reviewDetail.jsp";
						 break;
			}		 
		} else {
			request.setAttribute("error-msg", "게시글 상세 조회 실패");
			
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
