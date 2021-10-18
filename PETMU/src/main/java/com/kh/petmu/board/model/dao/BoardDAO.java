package com.kh.petmu.board.model.dao;

import static com.kh.petmu.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.petmu.board.model.vo.Board;
import com.kh.petmu.comment.model.vo.Comment;

public class BoardDAO {
	private Properties prop;
	
	public BoardDAO() {
		prop = new Properties();
		
		String filePath = BoardDAO.class.getResource("/config/board-sql.properties").getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getListCount(Connection con, int cateNo) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getListCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cateNo);
			rs = ps.executeQuery();

			//rs의 값 가져오기
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

	public ArrayList<Board> selectList(Connection con, int cateNo,int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, cateNo);
			ps.setInt(2, endRow);
			ps.setInt(3, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));

				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		
		return list;
	}
	
	public Board selectBoard(Connection con, int bno) {
		Board b = new Board();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));
								
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return b;
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

	public int insertBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("insertBoard");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, b.getCateNo());
			ps.setString(2, b.getBwriterId());
			ps.setString(3, b.getBtitle());
			ps.setString(4, b.getBcontent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}

	public int updateBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateBoard");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, b.getBtitle());
			ps.setString(2, b.getBcontent());
			ps.setInt(3, b.getBno());
			
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

	public int getTitleListCount(Connection con, int cateNo, String keyword) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getTitleListCount");

		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cateNo);
			ps.setString(2, keyword);
			
			rs = ps.executeQuery();
			
			//rs의 값 가져오기
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
	
	public int getWriterIdListCount(Connection con, int cateNo, String keyword) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getWriterIdListCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cateNo);
			ps.setString(2, keyword);
			
			rs = ps.executeQuery();
			
			//rs의 값 가져오기
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
	
	public int getWriterNickListCount(Connection con, int cateNo, String keyword) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("getWriterNickListCount");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cateNo);
			ps.setString(2, keyword);
			
			rs = ps.executeQuery();
			
			//rs의 값 가져오기
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
	
	public ArrayList<Board> selectTitleList(Connection con, int cateNo, int currentPage, int limit, String keyword) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectTitleList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, cateNo);
			ps.setInt(2, endRow);
			ps.setString(3, keyword);
			ps.setInt(4, startRow);
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));

				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		
		return list;
	}

	public ArrayList<Board> selectWriterIdList(Connection con, int cateNo, int currentPage, int limit, String keyword) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectWriterIdList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, cateNo);
			ps.setInt(2, endRow);
			ps.setString(3, keyword);
			ps.setInt(4, startRow);
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));

				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		
		return list;
	}

	public ArrayList<Board> selectWriterNickList(Connection con, int cateNo, int currentPage, int limit, String keyword) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectWriterNickList");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, cateNo);
			ps.setInt(2, endRow);
			ps.setString(3, keyword);
			ps.setInt(4, startRow);
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));

				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		
		return list;
	}


	public ArrayList<Board> selectListSortcmt(Connection con, int cateNo, int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectListcmt");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, cateNo);
			ps.setInt(2, endRow);
			ps.setInt(3, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));

				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		
		return list;
	}
	   
	
	public ArrayList<Board> selectListSortlike(Connection con, int cateNo, int currentPage, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectListSortlike");
		
		try {
			ps = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit - 1;
			
			ps.setInt(1, cateNo);
			ps.setInt(2, endRow);
			ps.setInt(3, startRow);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBno(rs.getInt("bno"));
				b.setCateNo(rs.getInt("cate_no"));
				b.setBwriterId(rs.getString("bwriter_id"));
				b.setBwriterNick(rs.getString("bwriter_nick"));
				b.setBtitle(rs.getString("btitle"));
				b.setBcontent(rs.getString("bcontent"));
				b.setBcount(rs.getInt("bcount"));
				b.setBfile(rs.getString("bfile"));
				b.setLikeCount(rs.getInt("likecount"));
				b.setBdate(rs.getDate("bdate"));
				b.setStatus(rs.getString("status"));
				b.setCcount(rs.getInt("ccount"));

				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		
		return list;
	}
	   
	
	
	
	// 민수님 작성
	

	   public int getPersonalListCount(Connection con, String nickname) {
	      int result = 0;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql = prop.getProperty("getPersonalListCount");
	      
	      try {
	         ps = con.prepareStatement(sql);
	         
	         ps.setString(1, nickname);
	         
	         rs = ps.executeQuery();
	         
	         //rs의 값 가져오기
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
	   
	   
	   
	   public ArrayList<Board> selecPersonaltList(Connection con, String nickname, int currentPage, int limit) {
		      ArrayList<Board> list = new ArrayList<>();
		      PreparedStatement ps = null;
		      ResultSet rs = null;
		      
		      String sql = prop.getProperty("selectPersonalList");
		      
		      try {
		         ps = con.prepareStatement(sql);
		         
		         int startRow = (currentPage - 1) * limit + 1;
		         int endRow = startRow + limit - 1;
		         
		         ps.setString(1, nickname);
		         ps.setInt(2, endRow);
		         ps.setInt(3, startRow);
		         
		         rs = ps.executeQuery();
		         
		         while(rs.next()) {
		            Board b = new Board();
		            
		            b.setBno(rs.getInt("bno"));
		            b.setCateNo(rs.getInt("cate_no"));
		            b.setBwriterId(rs.getString("bwriter_id"));
		            b.setBwriterNick(rs.getString("bwriter_nick"));
		            b.setBtitle(rs.getString("btitle"));
		            b.setBcontent(rs.getString("bcontent"));
		            b.setBcount(rs.getInt("bcount"));
		            b.setBfile(rs.getString("bfile"));
		            b.setLikeCount(rs.getInt("likecount"));
		            b.setBdate(rs.getDate("bdate"));
		            b.setStatus(rs.getString("status"));

		            list.add(b);
		            
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





