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
 * Servlet implementation class BoardUpdate
 */
@WebServlet("/update.bo")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		// 게시글 저장 vo
		Board b = new Board();
		
		BoardService service = new BoardService();
		
		b = service.updateView(bno);
		
		b.setBtitle(btitle);
		b.setBcontent(bcontent);
		
		int result = service.updateBoard(b);
		
		String page = "";
		
		if(result > 0) {
			
			request.setAttribute("cate", cateNo);
			
				switch(cateNo) {
				case 3 : page = request.getContextPath() + "/selectOne.bo?cate=3&bno=" + bno;
						 break;
				case 4 : page = request.getContextPath() + "/selectOne.bo?cate=4&bno=" + bno;
						 break;
				case 5 : page = request.getContextPath() + "/selectOne.bo?cate=5&bno=" + bno;
				 		 break;
				case 6 : page = request.getContextPath() + "/selectOne.bo?cate=6&bno=" + bno;
						 break;
				case 7 : page = request.getContextPath() + "/selectOne.bo?cate=7&bno=" + bno;
				 		 break;
				case 8 : page = request.getContextPath() + "/selectOne.bo?cate=8&bno=" + bno;
						 break;		 
						 
				}
			
			response.sendRedirect(page);
		} else {
			request.setAttribute("error-msg", "게시글 수정에 실패하였습니다.");
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
