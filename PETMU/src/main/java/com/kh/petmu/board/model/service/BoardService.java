package com.kh.petmu.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.petmu.board.model.dao.BoardDAO;
import com.kh.petmu.board.model.vo.Board;
import com.kh.petmu.comment.model.vo.Comment;

import static com.kh.petmu.common.JDBCTemplate.*;

public class BoardService {
	Connection con;
	BoardDAO dao = new BoardDAO();
	
	public int getListCount(int cateNo) {
		con = getConnection();
		int result = dao.getListCount(con, cateNo);
		
		close(con);
		
		return result;
	}

	public ArrayList<Board> selectList(int cateNo, int currentPage, int limit) {
		con = getConnection();
		
		ArrayList<Board> list = dao.selectList(con, cateNo, currentPage, limit);

		close(con);
		
		return list;
	}
	
	public Board selectBoard(int bno) {
		con = getConnection();
		
		Board b = dao.selectBoard(con, bno);
		
		// 조회수 증가
		if(b != null) {

			b.setBcount(b.getBcount() + 1);
			
			int result = dao.updateReadCount(con, bno);
			
			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		}
		
		close(con);
				
		return b;
	}

	public ArrayList<Comment> selectCoList(int bno) {
		con = getConnection();
		
		ArrayList<Comment> cl = dao.selectCoList(con, bno);
		
		close(con);
		
		return cl;
	}

	public int getCmtCount(int bno) {
		con = getConnection();
		
		int result = dao.getCmtCount(con, bno);
		
		close(con);
		
		return result;
	}

	public int insertBoard(Board b) {
		con = getConnection();
		
		int result = dao.insertBoard(con, b);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public Board updateView(int bno) {
		con = getConnection();
		
		Board b = dao.selectBoard(con, bno);
		
		close(con);
		
		return b;
	}

	public int updateBoard(Board b) {
		con = getConnection();
		
		int result = dao.updateBoard(con, b);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int deleteBoard(int bno) {
		con = getConnection();
		
		int result = dao.deleteBoard(con, bno);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	public int getTitleListCount(int cateNo, String keyword) {
		con = getConnection();
		int result = dao.getTitleListCount(con, cateNo, keyword);
		
		close(con);
		
		return result;
	}

	public int getWriterIdListCount(int cateNo, String keyword) {
		con = getConnection();
		int result = dao.getWriterIdListCount(con, cateNo, keyword);
		
		close(con);
		
		return result;
	}
	
	public int getWriterNickListCount(int cateNo, String keyword) {
		con = getConnection();
		int result = dao.getWriterNickListCount(con, cateNo, keyword);
		
		close(con);
		
		return result;
	}
	
	public ArrayList<Board> selectTitleList(int cateNo, int currentPage, int limit, String keyword) {
		con = getConnection();
		
		ArrayList<Board> list = dao.selectTitleList(con, cateNo, currentPage, limit, keyword);

		close(con);
		
		return list;
	}

	public ArrayList<Board> selectWriterIdList(int cateNo, int currentPage, int limit, String keyword) {
		con = getConnection();
		
		ArrayList<Board> list = dao.selectWriterIdList(con, cateNo, currentPage, limit, keyword);

		close(con);
		
		return list;
	}
	
	public ArrayList<Board> selectWriterNickList(int cateNo, int currentPage, int limit, String keyword) {
		con = getConnection();
		
		ArrayList<Board> list = dao.selectWriterNickList(con, cateNo, currentPage, limit, keyword);

		close(con);
		
		return list;
	}

	public ArrayList<Board> selectListSortcmt(int cateNo, int currentPage, int limit) {
		con = getConnection();
		
		ArrayList<Board> list = dao.selectListSortcmt(con, cateNo, currentPage, limit);
		
		close(con);
		
		return list;
	}

	public ArrayList<Board> selectListSortlike(int cateNo, int currentPage, int limit) {
		con = getConnection();
		
		ArrayList<Board> list = dao.selectListSortlike(con, cateNo, currentPage, limit);
		
		close(con);
		
		return list;
	}

	
	
	// 민수님 작성
	
	public ArrayList<Board> selectPersonalList(String nickname, int currentPage, int limit) {
	      con = getConnection();
	      
	      ArrayList<Board> list = dao.selecPersonaltList(con, nickname, currentPage, limit);

	      close(con);
	      
	      return list;
	}
	   
	
   public int getPersonalListCount(String nickname) {
	      con = getConnection();
	      int result = dao.getPersonalListCount(con, nickname);
	      
	      close(con);
	      
	      return result;
   }




}
