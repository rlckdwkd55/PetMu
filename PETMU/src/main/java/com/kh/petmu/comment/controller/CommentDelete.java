package com.kh.petmu.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.comment.model.service.CommentService;

/**
 * Servlet implementation class CommentDelete
 */
@WebServlet("/cmtDelete.co")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cno = Integer.parseInt(request.getParameter("cno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		//System.out.println("서블릿 : " + cno); //확인 완료
		//System.out.println("서블릿 : " + bno); //확인 완료
		
		int result = new CommentService().deleteComment(cno, bno);
		
		if(result > 0) {
			response.getWriter().print(result);
		} else {
			request.setAttribute("error-msg", "댓글 삭제 실패");
			request.getRequestDispatcher("views/common/errorPage").forward(request, response);
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
