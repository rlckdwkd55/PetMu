package com.kh.petmu.freeBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.freeBoard.model.service.BoardService;
import com.kh.petmu.comment.model.vo.Comment;
import com.kh.petmu.freeBoard.model.vo.freeBoard;

/**
 * Servlet implementation class BoardSelectOne
 */
@WebServlet("/selectOne.fb")
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
		
		BoardService service = new BoardService();
		
		freeBoard fb = service.selectOne(bno);
		ArrayList<Comment> coList = service.selectCoList(bno);
		
		int cmtCount = service.getCmtCount(bno);
		
		// System.out.println("[BNO : " + bno );
		
		String page = "";
		
		if( fb != null ) {
			request.setAttribute("freeBoard", fb);
			request.setAttribute("coList", coList);
			request.setAttribute("cmtCount", cmtCount);
			
			page = "views/freeBoard/boardDetail.jsp";
		} else {
			request.setAttribute("error-msg", "게시글 상세조회 실패!");
			
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



