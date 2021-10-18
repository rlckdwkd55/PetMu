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
 * Servlet implementation class BoardInsert
 */
@WebServlet("/insert.bo")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		

		
		String bwriterId = request.getParameter("bwriterId");
		String bwriterNick = request.getParameter("bwriterNick");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		// 게시글 저장 vo
		Board b = new Board();
		
		b.setBwriterId(bwriterId);
		b.setBwriterNick(bwriterNick);
		b.setBtitle(btitle);
		b.setBcontent(bcontent);
		b.setCateNo(cateNo);

		BoardService service = new BoardService();
		
		int result = service.insertBoard(b);
		
		String page = "";
		
		if(result > 0) {
			request.setAttribute("cate", cateNo);
			
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
			request.setAttribute("error-msg", "게시글 작성에 실패하였습니다.");
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
