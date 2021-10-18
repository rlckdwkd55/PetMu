package com.kh.petmu.member.model.dao;

import static com.kh.petmu.common.JDBCTemplate.*;

import static com.kh.petmu.common.JDBCTemplate.close;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.petmu.member.model.vo.Member;
import com.kh.petmu.member.model.vo.SerchMember;

public class MemberDAO {
	// key와 value가 모두 문자열로 이루어진 
	// 설정 정보를 저장하는 전용 Map 객체
	private Properties prop;
	
	public MemberDAO() {
		prop = new Properties();
		
		// 파일 불러오기
		// 1) 파일 경로 가져오기
		String filePath = MemberDAO.class
				          .getResource("/config/member-sql.properties")
				          .getPath();
		
		// 2) 파일 불러오기(load)
		try {
			prop.load(new FileReader(filePath));
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}	
	
	public int insertMember(Connection con, Member m) {
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			ps = con.prepareStatement(sql);
			
			// 데이터베이스 숫자 시작은 1부터
			ps.setString(1, m.getUserId());
			ps.setString(2, m.getUserPwd());
			ps.setString(3, m.getNickname());
			ps.setString(4, m.getUserName());			
			ps.setDate(5, m.getBirth());
			ps.setString(6, m.getEmail());
			ps.setString(7, m.getAddress());
			ps.setString(8, m.getPetType());
			ps.setString(9, m.getPetName());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(ps);
		}
		System.out.println("RESULT :" + result);
		return result;
	}

	
	public int deleteMember(Connection con, String userId) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
		
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userId);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(ps);
		}
		
		return result;
	}

	public Member selectMember(Connection con, Member loginMember) {
	
		Member result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			// 2. ps 에 준비한 쿼리 연결
			ps = con.prepareStatement(sql);
			
			// 3. sql ? 채우기
			ps.setString(1, loginMember.getUserId());
			ps.setString(2, loginMember.getUserPwd());
			
			// 4. rs (select 실행 결과) 받아오기
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = new Member();
				
				result.setUserId(  rs.getString("USERID") );
				result.setUserPwd( rs.getString("password"));
				result.setNickname(rs.getString("nickname"));
				result.setUserName(rs.getString("userName"));
				result.setBirth(   rs.getDate("birth"));
				result.setEmail(   rs.getString("email"));
				result.setAddress( rs.getString("address"));
				result.setPetType( rs.getString("pet_Type"));
				result.setPetName( rs.getString("pet_Name"));
				
			}
			
			System.out.println("조회 결과 : " + result);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return result;
	}

	public int idcheck(Connection con, String userId) {
		int result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("idcheck");
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, userId);
			
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


public int nicknamecheck(Connection con, String nickname) {
	int result = 0;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = prop.getProperty("idcheck");
	
	try {
		ps = con.prepareStatement(sql);
		
		ps.setString(1, nickname);
		
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

public int emailcheck(Connection con, String email) {
	int result = 0;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = prop.getProperty("emailcheck");
	
	try {
		ps = con.prepareStatement(sql);
		
		ps.setString(1, email);
		
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

public int updateMember(Connection con, Member m) {
	int result = 0;
	PreparedStatement ps = null;
	String sql = prop.getProperty("updateMember");
	
	try {
	
		ps = con.prepareStatement(sql);
		ps.setString(1, m.getUserPwd() );		
    	ps.setString(2, m.getAddress() );	
    	ps.setString(3, m.getPetType());
    	ps.setString(4, m.getPetName());
		ps.setString(5, m.getUserId() );		
		result = ps.executeUpdate();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	} finally {
		
		close(ps);
	}
			
	return result;
}

public int nicknameUpdate(Connection con, Member m) {
	int result = 0;
	PreparedStatement ps = null;
	String sql = prop.getProperty("nicknameUpdate");
	
	try {
	
		ps = con.prepareStatement(sql);

		ps.setString(1, m.getNickname() );
		ps.setString(2, m.getUserId() );
		
		result = ps.executeUpdate();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	} finally {
		
		close(ps);
	}
			
	return result;
}

public String memberSerch(Connection con, SerchMember serchMember) {
	String id = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	System.out.println("DAO 전달 확인");
	String sql = prop.getProperty("serchMember");
	
	try {
		ps = con.prepareStatement(sql);
		
		ps.setString(1, serchMember.getUserName());
		ps.setString(2, serchMember.getEmail());
		rs = ps.executeQuery();
		if(rs.next()) {
			id = rs.getString("userId");
			
		} 
		
		
		
	} catch (SQLException e) {
	
		e.printStackTrace();
	} finally {
		
		close(rs);
		close(ps);
		
		
		
	}
	
	System.out.println("id = " + id);
	return id;
	
}

public int pwdChange(Connection con, Member m) {
	
	String userPwd = m.getUserPwd();
	String userId = m.getUserId();
	String userName = m.getUserName();
	String email = m.getEmail();
	
	
	
	String id = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int result = 0;
	System.out.println("DAO 입력 확인" + userPwd  + "/" + userId  + "/" + userName  + "/" + email);
	String sql = prop.getProperty("userpwdcahnge");
	
	try {
		ps = con.prepareStatement(sql);
		
		ps.setString(1, m.getUserPwd());
		ps.setString(2, userId);
		ps.setString(3, userName);
		ps.setString(4, email);
		result = ps.executeUpdate();
		
	} catch (SQLException e) {

		
		e.printStackTrace();
	} finally {
		close(ps);
	}
	System.out.println("PW변경 RESULT :" + result);
	return result;
}

}









