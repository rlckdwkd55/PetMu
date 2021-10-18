package com.kh.petmu.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.member.model.service.MemberService;
import com.kh.petmu.member.model.vo.Member;
import com.kh.petmu.member.model.vo.SerchMember;

/**
 * Servlet implementation class MemberIdSerch
 */
@WebServlet("/idserch.do")
public class MemberIdSerch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdSerch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		

		System.out.println("서블릿 전달 확인 : " + userName + "/" + email);
		
		SerchMember serchMember = new SerchMember(userName, email);
		
		MemberService service = new MemberService();
		
		String result = service.memberSerch(serchMember);

		System.out.println("서블릿에서 받은 result = " + result);
		
		
		response.getWriter().print(result);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
