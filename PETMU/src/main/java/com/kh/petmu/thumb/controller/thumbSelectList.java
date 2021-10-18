package com.kh.petmu.thumb.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.thumb.model.vo.PageInfo;
import com.kh.petmu.thumb.model.service.ThumbnailService;
import com.kh.petmu.thumb.model.vo.Thumbnail;

/**
 * Servlet implementation class thumbnailSelectList
 */
@WebServlet("/selectList.tn")
public class thumbSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thumbSelectList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Thumbnail> list = new ArrayList<>();
		ThumbnailService service = new ThumbnailService();
		
		// 제목 or 작성자로 검색 데이터
		String target = request.getParameter("searchTarget");
		String keyword = request.getParameter("keyword");
		
		// 1, 2, 3, 4, 5
		int startPage, endPage;
		
		// 55개 게시글 -> 10개 - 페이지 1 --> 6개 페이지
		int maxPage;
		
		// 현재 사용자가 보는 페이지
		int currentPage = 1;
		
		// 페이지당 게시글 수
		int limit = 10;
		
		// 만약 사용자가 특정 페이지 정보를 가져왔다면
		if( request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 총 게시글 수 조회
		int listCount = 0;
		
		if(target == null && keyword == null) {
			listCount = service.getListCount();
			
		} else if(target.equals("title") && keyword != null) {
			listCount = service.getTitleListCount(keyword);
			
		} else if(target.equals("writerId") && keyword != null) {
			listCount = service.getWriterIdListCount(keyword);
			
		} else if(target.equals("writerNick") && keyword != null) {
			listCount = service.getWriterNickListCount(keyword);
		}
		
		maxPage = (int)((double)listCount/limit + 0.9);
		
		startPage = (int)(((double)currentPage/limit + 0.9) - 1) * limit + 1;
		
		endPage = startPage + limit - 1; 
		
		// 만약 마지막 페이지가 endPage보다 작다면
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// ------------- 페이지 처리 끝! ----------- //
		
		if(target == null && keyword == null) {
			list = service.selectList(currentPage, limit);
			
		} else if(target.equals("title") && keyword != null) {
			list = service.selectTitleList(currentPage, limit, keyword);
			
		} else if(target.equals("writerId") && keyword != null) {
			list = service.selectWriterIdList(currentPage, limit, keyword);
			
		} else if(target.equals("writerNick") && keyword != null) {
			list = service.selectWriterNickList(currentPage, limit, keyword);
			
		}
		
		String page = "";
		
		if ( list != null ) {
			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);

			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			page = "views/thumb/thumbList.jsp";
		} else {
			request.setAttribute("error-msg",  "사진 게시글 목록 조회 실패");
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
