package com.kh.petmu.comment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.petmu.comment.model.vo.Comment;
import static com.kh.petmu.common.JDBCTemplate.*;

public class CommentDAO {
	
	private Properties prop;
	
	public CommentDAO() {
		prop = new Properties();
		
		String filePath = CommentDAO.class.getResource("/config/comment-sql.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertComment(Connection con, Comment co) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, co.getBno());
			ps.setString(2, co.getCwriterId());
			ps.setString(3, co.getCcontent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public int deleteComment(Connection con, int cno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("deleteComment");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cno);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public int updateComment(Connection con, int cno, String content) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateComment");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, content);
			ps.setInt(2, cno);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		
		return result;
	}

	public int updateCmtCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateCmtCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public int minusCmtCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("minusCmtCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

}















