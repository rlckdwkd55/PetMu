package com.kh.petmu.thumb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.petmu.comment.model.vo.Comment;
import com.kh.petmu.thumb.model.service.ThumbnailService;

/**
 * Servlet implementation class ThumbSelectOne
 */
@WebServlet("/selectOne.tn")
public class ThumbSelectOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbSelectOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		HashMap<String, Object> thumb;
		ThumbnailService service = new ThumbnailService();
		ArrayList<Comment> coList = service.selectCoList(bno);
		
		int cmtCount = service.getCmtCount(bno);
		thumb = service.selectOne(bno);
		
		String page="";
		
		if( thumb != null && thumb.get("thumb") != null ) {
			request.setAttribute("thumbnail", thumb.get("thumb"));
			request.setAttribute("fileList", thumb.get("list"));
			request.setAttribute("coList", coList);
			request.setAttribute("cmtCount", cmtCount);
			
			page="views/thumb/thumbDetail.jsp";
		} else {
			request.setAttribute("error-msg", "게시글 상세 조회 실패!");
			
			page= "views/common/errorPage.jsp";
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
