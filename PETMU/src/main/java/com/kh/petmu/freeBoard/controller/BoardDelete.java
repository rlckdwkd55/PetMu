package com.kh.petmu.freeBoard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.petmu.freeBoard.model.service.BoardService;
import com.kh.petmu.freeBoard.model.vo.freeBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardDelete
 */
@WebServlet("/delete.fb")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String savePath = request.getServletContext().getRealPath("resources/boardUploadFiles");
		

		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardService service = new BoardService();
		
		freeBoard fb = service.updateView(bno);
		
		// 삭제할 게시글의 첨부파일까지 삭제 
		if( fb.getBfile()!= null ) {
			new File(savePath + "/" + fb.getBfile()).delete();
		}
		
		int result = service.deleteBoard(bno);
		
		if (result > 0) {
			response.sendRedirect("selectList.fb");
		} else {
			request.setAttribute("error-msg", "게시글 삭제 오류 발생!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
