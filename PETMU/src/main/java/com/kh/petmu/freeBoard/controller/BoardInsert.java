package com.kh.petmu.freeBoard.controller;

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
 * Servlet implementation class BoardInsert
 */
@WebServlet("/insert.fb")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 업로드용 서블릿
		// MultipartRequest
		
		// 1. 전송받은 최대크기 설정하기
		int maxSize = 1024 * 1024 * 10; // 10MB
		
		// 2. multipart/form-data 형식으로 인코딩 되어 왔는지 확인
		if(! ServletFileUpload.isMultipartContent(request)) {
			
			//에러 페이지
			request.setAttribute("error-msg", "multipart로 전송되지 않았습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
		}
		
		// 3. 받아온 파일을 저장할 경로 설정하기
		String root = request.getServletContext().getRealPath("/");
		String savePath = root + "resources/boardUploadFiles";
		
		// 4. 위에서 설정한 정보를 바탕으로 MultipartRequest 생성하기
		MultipartRequest mre = new MultipartRequest(request, savePath,
				                                    maxSize, "UTF-8",
				                                    new DefaultFileRenamePolicy());
		
		// 5-1. 파일이 아닌 기본 전송값 처리
		
		String bwriterId = mre.getParameter("bwriterId");
		String bwriterNick = mre.getParameter("bwriterNick");
		String btitle = mre.getParameter("btitle");
		String bcontent = mre.getParameter("bcontent");
		
		// 5-2. 파일 저장 및 정보 처리
		String filename = mre.getFilesystemName("bfile");
		
		// 6. 전달받은 값을 서비스로 넘기기
		freeBoard fb = new freeBoard(bwriterId, bwriterNick, btitle, bcontent, filename);
		
		int result = new BoardService().insertBoard(fb);
		
		if (result > 0) {
			response.sendRedirect("selectList.fb");
		} else {
			request.setAttribute("error-msg", "게시글 작성 실패!");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
			       .forward(request, response);
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
