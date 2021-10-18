package com.kh.petmu.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.petmu.member.model.vo.Member;
import com.kh.petmu.member.model.service.MemberService;

/**
 * Servlet implementation class MemberNicknameUpdate
 */
@WebServlet("/nicknameUpdate.do")
public class MemberNicknameUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberNicknameUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//닉네임 변경창에서 입력한 닉네임 받아오기
		
		String nickname = request.getParameter("nickname");
		
		
		// 해당 회원을 구분짓는 ID 받아오기
        HttpSession session = request.getSession(false);
		
		Member m = (Member)session.getAttribute("member");
		
		//변경한 닉네임 등록하기
		
		m.setNickname(nickname);
		
		System.out.println("변경한 회원 정보 확인 : " + m);
		
        MemberService service = new MemberService();
		
		int result = service.nicknameUpdate(m);
		
		if(result > 0) {
			
			// session.setAttribute("member", m);
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		} else {
			
			RequestDispatcher view
				= request.getRequestDispatcher("views/common/errorPage.jsp");
			
			request.setAttribute("error-msg", "회원 정보 수정 실패");
			
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
