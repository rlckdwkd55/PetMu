package com.kh.petmu.freeBoard.model.dao;

import static com.kh.petmu.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.petmu.comment.model.vo.Comment;
import com.kh.petmu.freeBoard.model.vo.freeBoard;

public class BoardDAO {
	private Properties prop;
	
	public BoardDAO() {
		prop = new Properties();
		String filePath = BoardDAO.class
				          .getResource("/config/freeboard-sql.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public int getListCount(Connection con) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("listCount");
		
		try {
			ps = con.prepareStatement(sql);
			
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

	public ArrayList<freeBoard> selectList(Connection con, int currentPage, int limit) {
		ArrayList<freeBoard> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeBoard fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
				
				list.add(fb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
		
		
		}


	public freeBoard selectOne(Connection con, int bno) {
		freeBoard fb = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectOne");
		// System.out.println("[DAO] : bno = " + bno);
		// System.out.println("[DAO] : sql = " + sql);
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));	
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return fb;
		
	}
	

	public int updateReadCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("updateReadCount");
		
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

	public int insertBoard(Connection con, freeBoard fb) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("insertBoard");
//		 System.out.println("[DAO] : fb = " + fb);
//		 System.out.println("[DAO] : sql = " + sql);
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, fb.getbwriterId());
			ps.setString(2, fb.getbtitle());
			ps.setString(3, fb.getBcontent());
			ps.setString(4, fb.getBfile());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		System.err.println("[DAO] : " + result);
		
		return result;
	}


	public int updateBoard(Connection con, freeBoard fb) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, fb.getbtitle() );
			ps.setString(2, fb.getBcontent() );
			ps.setString(3, fb.getBfile() );
			ps.setInt(4, fb.getBno() );
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}


	public int deleteBoard(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("deleteBoard");
		
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
	
	
	public ArrayList<Comment> selectCoList(Connection con, int bno) {
		ArrayList<Comment> cl = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectCoList");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Comment co = new Comment();
				
				co.setCno(rs.getInt("cno"));
				co.setBno(rs.getInt("bno"));
				co.setCwriterId(rs.getString("cwriter_id"));
				co.setCwriterNick(rs.getString("cwriter_nick"));
				co.setCcontent(rs.getString("ccontent"));
				co.setCdate(rs.getDate("cdate"));
				co.setStatus(rs.getString("status"));
				
				cl.add(co);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
			
		return cl;
	}
	
	public int getCmtCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getCmtCount");
		
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

	public int getTitleListCount(Connection con, String keyword) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getTitleListCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, keyword);
			
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

	public int getWriterIdListCount(Connection con, String keyword) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getWriterIdListCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, keyword);
			
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

	public int getWriterNickListCount(Connection con, String keyword) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getWriterNickListCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, keyword);
			
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

	public ArrayList<freeBoard> selectTitleList(Connection con, int currentPage, int limit, String keyword) {
		ArrayList<freeBoard> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectTitleList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, endRow);
			ps.setString(2, keyword);
			ps.setInt(3, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeBoard fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));	
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
				
				list.add(fb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}

	public ArrayList<freeBoard> selectWriterIdList(Connection con, int currentPage, int limit, String keyword) {
		ArrayList<freeBoard> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectWriterIdList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, endRow);
			ps.setString(2, keyword);
			ps.setInt(3, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeBoard fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));	
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
				
				list.add(fb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}

	public ArrayList<freeBoard> selectWriterNickList(Connection con, int currentPage, int limit, String keyword) {
		ArrayList<freeBoard> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectWriterNickList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, endRow);
			ps.setString(2, keyword);
			ps.setInt(3, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeBoard fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));	
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
				
				list.add(fb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}

	public ArrayList<freeBoard> selectListSortcmt(Connection con, int currentPage, int limit) {
		ArrayList<freeBoard> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectListcmt");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeBoard fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));	
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
				
				list.add(fb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}

	public ArrayList<freeBoard> selectListSortlike(Connection con, int currentPage, int limit) {
		ArrayList<freeBoard> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectListSortlike");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				freeBoard fb = new freeBoard();
				
				fb.setBno( rs.getInt("bno"));
				fb.setcateNo( rs.getInt("cate_No"));
				fb.setbwriterId( rs.getString("bwriter_Id"));
				fb.setbwriterNick( rs.getString("bwriter_Nick"));
				fb.setbtitle( rs.getString("btitle"));
				fb.setBcontent( rs.getString("bcontent"));
				fb.setBcount( rs.getInt("bcount"));
				fb.setBfile( rs.getString("bfile"));
				fb.setlikeCount( rs.getInt("likeCount"));
				fb.setBdate( rs.getDate("bdate"));	
				fb.setStatus(rs.getString("status"));
				fb.setCcount( rs.getInt("ccount"));
				
				list.add(fb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}






}


