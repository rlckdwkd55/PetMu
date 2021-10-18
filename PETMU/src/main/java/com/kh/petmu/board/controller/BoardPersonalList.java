package com.kh.petmu.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.board.model.service.BoardService;
import com.kh.petmu.board.model.vo.Board;
import com.kh.petmu.board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardPersonalList
 */
@WebServlet("/personallist.do")
public class BoardPersonalList extends HttpServlet {
	private static final long serialVersionUID = 4851L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardPersonalList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Board> list = new ArrayList<>();
		BoardService service = new BoardService();
		
		// request에 담긴 닉네임 정보 
		String nickname = request.getParameter("nickname");
		
		// 페이지 정보 startPage, endPage, maxPage, currentPage, limit, listCount
		int startPage, endPage;
		
		// 최대 페이지 수
		int maxPage;
		
		// 현재 페이지
		int currentPage = 1;
		
		// 페이지당 게시글 수
		int limit = 25;
		
		// 사용자가 특정 페이지 값을 가져왔다면
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 게시글 총 수
		int listCount = service.getPersonalListCount(nickname);
		
		// System.out.println("총 게시글 수 : " + listCount); 확인 완료!
		
		maxPage = (int)((double)listCount/limit + 0.9);
											
		startPage = (int)(((double)currentPage/10 + 0.9) - 1) * 10 + 1;
		
		endPage = startPage * 10 - 1;
		
		// 만약 maxPage < endPage라면
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// -------------------------페이지 처리 끝----------------------------------- //
		
		list = service.selectPersonalList(nickname, currentPage, limit);
		String page = "";
		
		if(list != null) {
			PageInfo pi = new PageInfo(startPage, endPage, maxPage, currentPage, limit, listCount);

			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pi);
			
			// 카테고리 번호에 맞는 게시글 페이지 등록
			if(nickname != null) {
				 page = "views/member/myPage_page.jsp";
				
						 
			}
		} else {
			request.setAttribute("error-msg", "마이페이지를 불러오는데 실패했습니다.");
			page = "views/common/errorPage.jsp";
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
