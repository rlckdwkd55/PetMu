package com.kh.petmu.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.comment.model.service.CommentService;
import com.kh.petmu.comment.model.vo.Comment;

/**
 * Servlet implementation class CommentInsert
 */
@WebServlet("/cmtInsert.co")
public class CommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax로 보낸 데이터 가져오기
		int bno = Integer.parseInt(request.getParameter("bno"));
		String cwriterId = request.getParameter("cwriterId");
		String cwriterNick = request.getParameter("cwriterNick");
		String ccontent = request.getParameter("ccontent");
		
		// 데이터 담을 vo
		Comment co = new Comment();
		
		co.setBno(bno);
		co.setCwriterId(cwriterId);
		co.setCwriterNick(cwriterNick);
		co.setCcontent(ccontent);
		
		// System.out.println(co); 확인 완료
		
		// 서비스로 보내기
		CommentService service = new CommentService();
		
		int result = service.insertComment(co, bno);
		
		if(result > 0) {
			response.getWriter().print(result);
		} else {
			request.setAttribute("error-msg", "댓글 입력 실패");
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
