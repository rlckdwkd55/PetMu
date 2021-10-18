package com.kh.petmu.thumb.model.service;

import static com.kh.petmu.common.JDBCTemplate.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.petmu.comment.model.vo.Comment;
import com.kh.petmu.freeBoard.model.vo.freeBoard;
import com.kh.petmu.thumb.model.dao.ThumbnailDAO;
import com.kh.petmu.thumb.model.vo.Attachment;
import com.kh.petmu.thumb.model.vo.Thumbnail;

public class ThumbnailService {
	private Connection con;
	private ThumbnailDAO dao = new ThumbnailDAO();
	
	public int getListCount() {
		con = getConnection();
		
		int result = dao.getListCount(con);
		
		close(con);
		
		return result;
	}
	
	public ArrayList<Thumbnail> selectList(int currentPage, int limit) {
		con = getConnection();
		ArrayList<Thumbnail> list = dao.selectList(con, currentPage, limit);
		
		close(con);
		
		return list;
	}


	public int insertThumbnail(Thumbnail t, ArrayList<Attachment> list) {
		con = getConnection();
		
		// 1. 사진 게시글 저장
		int result1 = dao.insertThumbnail(con, t);
		
		if (result1 > 0) {
			int bno = dao.getCurrentBno(con);
			
			for(int i = 0 ; i < list.size() ; i++) {
				list.get(i).setBno(bno);  // 최근 게시글 번호 전달
			}
		}
		
		// 2. 첨부 파일 저장
		int result2 = 0;
		for(int i = 0 ; i < list.size(); i++) {
			// 첫번째 이미지는 대표 이미지! FLEVEL = 1
			// 나머지 이미지는 FLEVEL = 2
			list.get(i).setFlevel(i == 0 ? 1 : 2);		
		
			result2 = dao.insertAttachment(con, list.get(i));
			
			if(result2 == 0) { // 잘못 실행된 첨부파일 insert가 있다면 반복을 취소
				break;
			}
		}
		
		// 3. 커밋과 롤백
		int totalResult = 0;
		
		if(result1 > 0 && result2 > 0) {
			commit(con);
			totalResult = 1;
		} else {
			rollback(con);
		}
		
		return totalResult;
	}

	public HashMap<String, Object> selectOne(int bno) {
		con = getConnection();
		
		HashMap<String, Object> hmap = dao.selectOne(con, bno);
		
		if( hmap != null && hmap.get("thumb") != null) {
			Thumbnail t = (Thumbnail)hmap.get("thumb");
			
			t.setBcount(t.getBcount() + 1);
			
			int result = dao.updateReadCount(con, bno);
			
			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		}		
		
		close(con);
		
		return hmap;
	}

	public HashMap<String, Object> getUpdateView(int bno) {
		con = getConnection();
		
		HashMap<String, Object> hmap = dao.selectOne(con, bno);
		
		close(con);
		
		return hmap;
	}

	public int updateThumbnail(Thumbnail t, ArrayList<Attachment> oldList, ArrayList<Attachment> newList) {
		con = getConnection();
		int result = 0;

		int result1 = dao.updateThumbnail(con, t); 
		
		if( result1 > 0) {
			int result2 = 0;
			
			for(Attachment a : oldList) { // 이전에 있던 사진 목록 
				result2 = dao.updateAttachment(con, a);
				
				if(result2 == 0) {
					break;
				}
			}
			
			for(Attachment a : newList) { // 새로 저장하는 사진 목록
				result2 = dao.insertAttachment(con, a);
				
				if(result2 == 0) {
					break;
				}
			}
			
			if(result2 > 0) {
				commit(con);
				result = 1;
			} else {
				rollback(con);
			}
		}
		
		close(con);
		
		return result;
	}

	public int deleteThumbnail(int bno, String savePath) {
		con = getConnection();
		
		HashMap<String, Object> hmap = dao.selectOne(con, bno);
		
		int result = dao.deleteThumbnail(con, bno);
		
		if( result > 0) {
			// 게시글 삭제 성공 -> 첨부파일 삭제
			result = dao.deleteAttachment(con, bno);
			
			for(Attachment a : (ArrayList<Attachment>)hmap.get("list")) {
				File f = new File(savePath + a.getChangename());
				
				f.delete();
			}
		}
		
		if( result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
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

public int getTitleListCount(String keyword) {
	con = getConnection();
	int result = dao.getTitleListCount(con, keyword);
	
	close(con);
	
	return result;
}

public int getWriterIdListCount(String keyword) {
	con = getConnection();
	int result = dao.getWriterIdListCount(con, keyword);
	
	close(con);
	
	return result;
}

public int getWriterNickListCount(String keyword) {
	con = getConnection();
	int result = dao.getWriterNickListCount(con, keyword);
	
	close(con);
	
	return result;
}

public ArrayList<Thumbnail> selectTitleList(int currentPage, int limit, String keyword) {
	con = getConnection();
	
	ArrayList<Thumbnail> list = dao.selectTitleList(con, currentPage, limit, keyword);

	close(con);
	
	return list;
}

public ArrayList<Thumbnail> selectWriterIdList(int currentPage, int limit, String keyword) {
	con = getConnection();
	
	ArrayList<Thumbnail> list = dao.selectTitleList(con, currentPage, limit, keyword);

	close(con);
	
	return list;
}

public ArrayList<Thumbnail> selectWriterNickList(int currentPage, int limit, String keyword) {
	con = getConnection();
	
	ArrayList<Thumbnail> list = dao.selectTitleList(con, currentPage, limit, keyword);

	close(con);
	
	return list;
}

public ArrayList<Thumbnail> selectListSortcmt(int currentPage, int limit) {
	con = getConnection();
	
	ArrayList<Thumbnail> list = dao.selectListSortcmt(con, currentPage, limit);

	close(con);
	
	return list;
}

public ArrayList<Thumbnail> selectListSortlike(int currentPage, int limit) {
	con = getConnection();
	
	ArrayList<Thumbnail> list = dao.selectListSortlike(con, currentPage, limit);

	close(con);
	
	return list;
}



}

