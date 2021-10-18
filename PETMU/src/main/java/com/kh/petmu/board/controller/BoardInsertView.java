package com.kh.petmu.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardInsertView
 */
@WebServlet("/insertView.bo")
public class BoardInsertView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		
		request.setAttribute("cate", cateNo);
		
		String page = "";
		
		switch(cateNo) {
		case 3 : page = "views/board/info_review/hospital/infoInsert.jsp";
				 break;
		case 4 : page = "views/board/info_review/hospital/reviewInsert.jsp";
				 break;
		case 5 : page = "views/board/info_review/place/infoInsert.jsp";
		 		 break;
		case 6 : page = "views/board/info_review/place/reviewInsert.jsp";
				 break;
		case 7 : page = "views/board/info_review/etc/infoInsert.jsp";
		 		 break;
		case 8 : page = "views/board/info_review/etc/reviewInsert.jsp";
				 break;		 
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
