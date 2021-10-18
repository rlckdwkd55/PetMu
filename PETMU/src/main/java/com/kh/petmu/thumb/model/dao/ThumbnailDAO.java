package com.kh.petmu.thumb.model.dao;

import static com.kh.petmu.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.petmu.comment.model.vo.Comment;
import com.kh.petmu.thumb.model.vo.Attachment;
import com.kh.petmu.thumb.model.vo.Thumbnail;

public class ThumbnailDAO {
	
	private Properties prop = null;
	
	public ThumbnailDAO() {
		prop = new Properties();
		
		String filePath = ThumbnailDAO.class
				          .getResource("/config/thumb-sql.properties").getPath();
		
		try {
			prop.load( new FileReader(filePath));
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


	public ArrayList<Thumbnail> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Thumbnail> list = new ArrayList<>();
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
				Thumbnail tn = new Thumbnail();
				
				tn.setBno( rs.getInt("bno"));
				tn.setcateNo( rs.getInt("cate_No"));
				tn.setbwriterId( rs.getString("bwriter_Id"));
				tn.setbwriterNick( rs.getString("bwriter_Nick"));
				tn.setbtitle( rs.getString("btitle"));
				tn.setBcontent( rs.getString("bcontent"));
				tn.setBcount( rs.getInt("bcount"));
				tn.setBfile( rs.getString("changename"));
				tn.setlikeCount( rs.getInt("likeCount"));
				tn.setBdate( rs.getDate("bdate"));
				tn.setStatus( rs.getString("status"));
				tn.setCcount( rs.getInt("ccount"));
				
				list.add(tn);
			}   
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return list;
	}

	public int insertThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("insertThumbnail");
		
//		System.out.println("[DAO] : t = " + t);
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, t.getbwriterId());
			ps.setString(2, t.getbtitle());
			ps.setString(3, t.getBcontent());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}


	public int insertAttachment(Connection con, Attachment attachment) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("insertAttachment");
		
		// System.out.println("[DAO] : attachment = " + attachment);
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, attachment.getBno());
			ps.setString(2, attachment.getOriginalname());
			ps.setString(3, attachment.getChangename());
			ps.setInt(4, attachment.getFlevel());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
	}
	
	public int getCurrentBno(Connection con) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String  sql = prop.getProperty("currentBno");
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
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

	public HashMap<String, Object> selectOne(Connection con, int bno) {
		HashMap<String, Object> hmap = new HashMap<>();
		ArrayList<Attachment> list = new ArrayList<>();
		Thumbnail t = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectOne");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, bno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				// 게시글 
				t = new Thumbnail();
				
				t.setBno(bno);
				t.setbwriterId( rs.getString("bwriter_Id"));
				t.setbwriterNick( rs.getString("bwriter_Nick"));
				t.setbtitle( rs.getString("btitle"));
				t.setBcontent( rs.getString("bcontent"));
				t.setBcount( rs.getInt("bcount"));
				t.setlikeCount( rs.getInt("likeCount"));
				t.setBdate( rs.getDate("bdate"));
				
				// 사진
				
				Attachment a = new Attachment();
				
				a.setFno( rs.getInt("fno"));
				a.setBno(bno);
				a.setOriginalname( rs.getString("originalname"));
				a.setChangename( rs.getString("changename"));
				a.setFlevel( rs.getInt("flevel"));
				a.setUploaddate( rs.getDate("uploaddate"));
				
				list.add(a);
					
			}
			
			hmap.put("thumb", t);
			hmap.put("list", list);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return hmap;
	}

	public int updateThumbnail(Connection con, Thumbnail t) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateThumb");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, t.getbtitle());
			ps.setString(2, t.getBcontent());
			ps.setInt(3, t.getBno());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}		
		
		return result;
	}

	public int updateAttachment(Connection con, Attachment a) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, a.getOriginalname());
			ps.setString(2, a.getChangename());
			ps.setInt(3, a.getFno());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
		return result;
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

	public int deleteThumbnail(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("deleteThumbnail");
		
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

	public int deleteAttachment(Connection con, int bno) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("deleteAttachment");
		
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

public ArrayList<Thumbnail> selectTitleList(Connection con, int currentPage, int limit, String keyword) {
	ArrayList<Thumbnail> list = new ArrayList<>();
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
			Thumbnail tn = new Thumbnail();
			
			tn.setBno( rs.getInt("bno"));
			tn.setcateNo( rs.getInt("cate_No"));
			tn.setbwriterId( rs.getString("bwriter_Id"));
			tn.setbwriterNick( rs.getString("bwriter_Nick"));
			tn.setbtitle( rs.getString("btitle"));
			tn.setBcontent( rs.getString("bcontent"));
			tn.setBcount( rs.getInt("bcount"));
			tn.setBfile( rs.getString("changename"));
			tn.setlikeCount( rs.getInt("likeCount"));
			tn.setBdate( rs.getDate("bdate"));	
			tn.setStatus(rs.getString("status"));
			tn.setCcount( rs.getInt("ccount"));
			
			
			list.add(tn);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(ps);
	}
	
	return list;
}

public ArrayList<Thumbnail> selectWriterIdList(Connection con, int currentPage, int limit, String keyword) {
	ArrayList<Thumbnail> list = new ArrayList<>();
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
			Thumbnail tn = new Thumbnail();
			
			tn.setBno( rs.getInt("bno"));
			tn.setcateNo( rs.getInt("cate_No"));
			tn.setbwriterId( rs.getString("bwriter_Id"));
			tn.setbwriterNick( rs.getString("bwriter_Nick"));
			tn.setbtitle( rs.getString("btitle"));
			tn.setBcontent( rs.getString("bcontent"));
			tn.setBcount( rs.getInt("bcount"));
			tn.setBfile( rs.getString("changename"));
			tn.setlikeCount( rs.getInt("likeCount"));
			tn.setBdate( rs.getDate("bdate"));	
			tn.setStatus(rs.getString("status"));
			tn.setCcount( rs.getInt("ccount"));
			
			list.add(tn);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(ps);
	}
	
	return list;
}

public ArrayList<Thumbnail> selectWriterNickList(Connection con, int currentPage, int limit, String keyword) {
	ArrayList<Thumbnail> list = new ArrayList<>();
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
			Thumbnail tn = new Thumbnail();
			
			tn.setBno( rs.getInt("bno"));
			tn.setcateNo( rs.getInt("cate_No"));
			tn.setbwriterId( rs.getString("bwriter_Id"));
			tn.setbwriterNick( rs.getString("bwriter_Nick"));
			tn.setbtitle( rs.getString("btitle"));
			tn.setBcontent( rs.getString("bcontent"));
			tn.setBcount( rs.getInt("bcount"));
			tn.setBfile( rs.getString("changename"));
			tn.setlikeCount( rs.getInt("likeCount"));
			tn.setBdate( rs.getDate("bdate"));	
			tn.setStatus(rs.getString("status"));
			
			list.add(tn);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(ps);
	}
	
	return list;
}

public ArrayList<Thumbnail> selectListSortcmt(Connection con, int currentPage, int limit) {
	ArrayList<Thumbnail> list = new ArrayList<>();
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
			Thumbnail tn = new Thumbnail();
			
			tn.setBno( rs.getInt("bno"));
			tn.setcateNo( rs.getInt("cate_No"));
			tn.setbwriterId( rs.getString("bwriter_Id"));
			tn.setbwriterNick( rs.getString("bwriter_Nick"));
			tn.setbtitle( rs.getString("btitle"));
			tn.setBcontent( rs.getString("bcontent"));
			tn.setBcount( rs.getInt("bcount"));
			tn.setBfile( rs.getString("changename"));
			tn.setlikeCount( rs.getInt("likeCount"));
			tn.setBdate( rs.getDate("bdate"));	
			tn.setStatus(rs.getString("status"));
			tn.setCcount( rs.getInt("ccount"));
			
			list.add(tn);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(ps);
	}
	
	return list;
}

public ArrayList<Thumbnail> selectListSortlike(Connection con, int currentPage, int limit) {
	ArrayList<Thumbnail> list = new ArrayList<>();
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
			Thumbnail tn = new Thumbnail();
			
			tn.setBno( rs.getInt("bno"));
			tn.setcateNo( rs.getInt("cate_No"));
			tn.setbwriterId( rs.getString("bwriter_Id"));
			tn.setbwriterNick( rs.getString("bwriter_Nick"));
			tn.setbtitle( rs.getString("btitle"));
			tn.setBcontent( rs.getString("bcontent"));
			tn.setBcount( rs.getInt("bcount"));
			tn.setBfile( rs.getString("changename"));
			tn.setlikeCount( rs.getInt("likeCount"));
			tn.setBdate( rs.getDate("bdate"));	
			tn.setStatus(rs.getString("status"));
			tn.setCcount( rs.getInt("ccount"));
			
			list.add(tn);
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




