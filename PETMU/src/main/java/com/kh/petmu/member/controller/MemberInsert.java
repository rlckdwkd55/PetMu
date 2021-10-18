package com.kh.petmu.member.controller;

import java.io.IOException;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.member.model.service.MemberService;
import com.kh.petmu.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsert
 */
@WebServlet("/insert.do")
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String nickname = request.getParameter("nickname");
		String userName = request.getParameter("userName");
	
		String bd = request.getParameter("birth");
	
	
		java.sql.Date birth=  java.sql.Date.valueOf(bd);
		
		
		String email = request.getParameter("email");
		String address = request.getParameter("zipCode") + "|"
				   + request.getParameter("address1") + "|"
				   + request.getParameter("address2");
		String petType= String.join(", ", request.getParameterValues("petType"));
		String petName= request.getParameter("petName");
		
		Member m = new Member(userId, userPwd, nickname, userName, birth, email, address, petType, petName , null, null);
		
		MemberService service = new MemberService();
		
		int result = service.insertMember(m);
		
		if(result > 0) {
			
			// 회원 가입 성공
			response.sendRedirect("index.jsp");
		} else {
			// 회원 가입 실패
			RequestDispatcher view = 
					request.getRequestDispatcher("views/common/errorPage.jsp");
			
			request.setAttribute("error-msg", "회원 가입 실패");
			
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
