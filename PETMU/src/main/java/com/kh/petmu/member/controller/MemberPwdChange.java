package com.kh.petmu.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.member.model.service.MemberService;
import com.kh.petmu.member.model.vo.Member;
import com.kh.petmu.member.model.vo.SerchMember;

/**
 * Servlet implementation class MemberPwdChange
 */
@WebServlet("/changepw.do")
public class MemberPwdChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String userPwd = request.getParameter("userPwd");
		

		System.out.println("서블릿 전달 확인 : " + userName + "/" + email + "/" + userId + "/" + userPwd);
		
		Member m = new Member(userId, userName, email, userPwd);
		
		MemberService service = new MemberService();
		
		int result = service.changePw(m);
		
		if(result > 0) {
			
			// 비밀번호 변경 성공!
			response.sendRedirect("views/member/login_page2.jsp");
		} else {
			// 비밀번호 변경 실패!
			RequestDispatcher view = 
					request.getRequestDispatcher("views/common/errorPage.jsp");
			
			request.setAttribute("error-msg", "비밀번호 변경 실패");
			
			view.forward(request, response);
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
