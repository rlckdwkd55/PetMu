package com.kh.petmu.like.model.dao;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.kh.petmu.common.JDBCTemplate.*;

public class likeDAO {
	private Properties prop;
	
	public likeDAO() {
		prop = new Properties();
		
		String filePath = likeDAO.class.getResource("/config/like-sql.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectLike(Connection con, int bno, String userId) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectLike");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			ps.setString(2, userId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return result;
	}

	public int updateLike(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateLike");
		
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

	public int getLikeCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("getLikeCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
	
		return result;
	}

	public int insertLike(Connection con, int bno, String userId) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("insertLike");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			ps.setString(2, userId);
			
			result = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}
	
	
	
	
}
