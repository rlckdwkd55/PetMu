package com.kh.petmu.member.model.service;

import static com.kh.petmu.common.JDBCTemplate.close;


import static com.kh.petmu.common.JDBCTemplate.commit;
import static com.kh.petmu.common.JDBCTemplate.getConnection;
import static com.kh.petmu.common.JDBCTemplate.rollback;
import static com.kh.petmu.common.JDBCTemplate.close;
import static com.kh.petmu.common.JDBCTemplate.commit;
import static com.kh.petmu.common.JDBCTemplate.getConnection;
import static com.kh.petmu.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.petmu.member.model.dao.MemberDAO;
import com.kh.petmu.member.model.vo.Member;
import com.kh.petmu.member.model.vo.SerchMember;

public class MemberService {

	private Connection con;
	private MemberDAO dao = new MemberDAO();
	
	public int insertMember(Member m) {
		con = getConnection();
		
		int result = dao.insertMember(con, m);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public Member selectMember(Member loginMember) {
		con = getConnection();
		
		Member result = dao.selectMember(con, loginMember);
		
		close(con);
		
		return result;
	}

	public int idcheck(String userId) {
		con = getConnection();
		
		int result = dao.idcheck(con, userId);
		
		close(con);
		
		return result;
	}
	
	public int nicknamecheck(String nickname) {
		con = getConnection();
		
		int result = dao.nicknamecheck(con, nickname);
		
		close(con);
		
		return result;
	}

	
	public int emailcheck(String email) {
con = getConnection();
		
		int result = dao.emailcheck(con, email);
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId) {
	con = getConnection();
		
		int result = dao.deleteMember(con, userId);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	
	}

	public int updateMember(Member m) {
con = getConnection();
		
		int result = dao.updateMember(con, m);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int nicknameUpdate(Member m) {
con = getConnection();
		
		int result = dao.nicknameUpdate(con, m);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public String memberSerch(SerchMember serchMember) {

	
	con = getConnection();
	
	System.out.println("서비스 전달 확인");
	
	String result = dao.memberSerch(con, serchMember);
	
	close(con);
	
	System.out.println("서비스에서 받은 result = " + result);
	
	return result;
	}

	public int changePw(Member m) {
		
		con = getConnection();
		

		String userPwd = m.getUserPwd();
		String userId = m.getUserId();
		String userName = m.getUserName();
		String email = m.getEmail();

		
		System.out.println("서비스 전달 확인" + userPwd + "/"  + userId + "/" + userName + "/" +email);
		
		int result = dao.pwdChange(con, m);
		
		close(con);
		
		System.out.println("서비스에서 받은 result = " + result);
		
		return result;
		}
}

