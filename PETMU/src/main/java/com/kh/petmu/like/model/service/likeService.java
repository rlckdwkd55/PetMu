package com.kh.petmu.like.model.service;

import java.sql.Connection;
import static com.kh.petmu.common.JDBCTemplate.*;
import com.kh.petmu.like.model.dao.likeDAO;

public class likeService {
	Connection con;
	likeDAO dao = new likeDAO();
	
	public int selectLike(int bno, String userId) {
		con = getConnection();
		
		int result = dao.selectLike(con, bno, userId);
		
		close(con);
		
		return result;
	}

	public int updateLike(int bno) {
		con = getConnection();
		
		int result = dao.updateLike(con, bno);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		return result;
	}

	public int getLikeCount(int bno) {
		con = getConnection();
		
		int result = dao.getLikeCount(con, bno);
		
		close(con);
		
		return result;
	}

	public int insertLike(int bno, String userId) {
		con = getConnection();
		
		int result = dao.insertLike(con, bno, userId);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	
	
	
}
