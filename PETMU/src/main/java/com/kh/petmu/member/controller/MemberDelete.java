package com.kh.petmu.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.petmu.member.model.service.MemberService;
import com.kh.petmu.member.model.vo.Member;

/**
 * Servlet implementation class MemberDelete
 */
@WebServlet("/delete.do")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 여기까지 문제 없음!
		System.out.println("탈퇴 서블릿 전달 확인 : " + userId + "/" + userPwd);
		
		Member loginMember = new Member(userId, userPwd);
		
		MemberService service = new MemberService();
		
		loginMember = service.selectMember(loginMember);
		
		System.out.println("받아온 탈퇴 회원 정보 확인 : " + loginMember);
		
		if( loginMember != null ) {
			// 로그인 성공!
			
			
			System.out.println("삭제할 회원 아이디 : " + userId);
			
			
			
			int result = service.deleteMember(userId);
			
			if( result > 0) {
				
				HttpSession session = request.getSession(false);
				
				session.invalidate();
				
				response.sendRedirect("index.jsp");
				
			} else {
				
				RequestDispatcher view
				= request.getRequestDispatcher("views/common/errorPage.jsp");
			
				request.setAttribute("error-msg", "회원 정보 삭제 실패");
				
				view.forward(request, response); }
				
				
		} else {
			// 로그인 실패!
			
			request.setAttribute("error-msg", "탈퇴 실패! 아이디나 비밀번호 확인 필요!");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);		
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
