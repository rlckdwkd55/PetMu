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
 * Servlet implementation class BoardList
 */
@WebServlet("/selectList.bo")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Board> list = new ArrayList<>();
		BoardService service = new BoardService();
		
		// 제목 or 작성자로 검색 데이터
		String target = request.getParameter("searchTarget");
		String keyword = request.getParameter("keyword");
		
		// request에 담긴 페이지 카테고리 번호
		int cateNo = Integer.parseInt(request.getParameter("cate"));
		
		// 페이지 정보 startPage, endPage, maxPage, currentPage, limit, listCount
		int startPage, endPage;
		
		// 최대 페이지 수
		int maxPage;
		
		// 현재 페이지
		int currentPage = 1;
		
		// 페이지당 게시글 수
		int limit = 20;
		
		// 사용자가 특정 페이지 값을 가져왔다면
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 게시글 총 수
		int listCount = 0;
		
		if(target == null && keyword == null) {
			// 검색을 하지 않았을 때
			listCount = service.getListCount(cateNo);
		} else if(target.equals("title") && keyword != null) {
			// 제목으로 검색
			listCount = service.getTitleListCount(cateNo, keyword);
		} else if(target.equals("writerId") && keyword != null) {
			// 작성자 아이디로 검색
			listCount = service.getWriterIdListCount(cateNo, keyword);
		} else if(target.equals("writerNick") && keyword != null) {
			// 작성자 닉네임으로 검색
			listCount = service.getWriterNickListCount(cateNo, keyword);
		}
		
		// System.out.println("총 게시글 수 : " + listCount); 확인 완료!
		
		maxPage = (int)((double)listCount/limit + 0.9);
											
		startPage = (int)(((double)currentPage/10 + 0.9) - 1) * 10 + 1;
		
		endPage = startPage * 10 - 1;
		
		// 만약 maxPage < endPage라면
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// -------------------------페이지 처리 끝----------------------------------- //
		

		
		// 게시글 리스트 가져오기
		if(target == null && keyword == null) {
			// 검색하지 않았을 때
			list = service.selectList(cateNo, currentPage, limit);
		} else if(target.equals("title") && keyword != null) {
			// 제목으로 검색
			list = service.selectTitleList(cateNo, currentPage, limit, keyword);
		} else if(target.equals("writerId") && keyword != null) {
			// 아이디로 검색
			list = service.selectWriterIdList(cateNo, currentPage, limit, keyword);
		} else if(target.equals("writerNick") && keyword != null) {
			// 닉네임으로 검색
			list = service.selectWriterNickList(cateNo, currentPage, limit, keyword);
		}
		
		String page2 = null;
		
		if(list != null) {
			PageInfo pi = new PageInfo(startPage, endPage, maxPage, currentPage, limit, listCount);
			
			request.setAttribute("cate", cateNo);
			request.setAttribute("list", list);
			request.setAttribute("pageInfo", pi);
			
			// 카테고리 번호에 맞는 게시글 페이지 등록
			switch(cateNo) {
				case 3 : page2 = "views/board/info_review/hospital/infoList.jsp";
						 break;
				case 4 : page2 = "views/board/info_review/hospital/reviewList.jsp";
						 break;
				case 5 : page2 = "views/board/info_review/place/infoList.jsp";
				 		 break;
				case 6 : page2 = "views/board/info_review/place/reviewList.jsp";
						 break;
				case 7 : page2 = "views/board/info_review/etc/infoList.jsp";
				 		 break;
				case 8 : page2 = "views/board/info_review/etc/reviewList.jsp";
						 break;		 
						 
			}
		} else {
			request.setAttribute("error-msg", "게시글 정보 불러오기에 실패했습니다.");
			page2 = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page2).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
