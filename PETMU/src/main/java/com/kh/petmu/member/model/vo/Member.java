package com.kh.petmu.member.model.vo;

import java.io.Serializable;

import java.sql.Date;

// VO(Value Object)
// 데이터 전달용 그릇 역할을 하는 클래스
public class Member implements Serializable {

	/**
	 * 다른 위치에 있는 동일한 Member 클래스가
	 * 현재 위치의 Member 클래스와 충돌할 경우
	 * 헷갈리지 않도록 주는 고유 속성
	 */
	private static final long serialVersionUID = 2803L;

   //1. 필드변수
	private String userId;
	private String userPwd;
	private String nickname;
	private String userName;
	private Date birth;
	private String email;
	private String address;
	private String petType;
	private String petName;
	private String levelType;
	private Date enrollDate;

	
	// 2. 생성자
	public Member() { }

	public Member(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}
	
	public Member(String userId, String userName, String email, String userPwd) {
		super();
		
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.userPwd = userPwd; 
	
		
	
	}
	
	
	

	public Member(String userId, String userPwd, String nickname, String userName, Date birth, String email,
			String address, String petType, String petName, String levelType, Date enrollDate) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.nickname = nickname;
		this.userName = userName;
		this.birth = birth;
		this.email = email;
		this.address = address;
		this.petType = petType;
		this.petName = petName;
		this.levelType = levelType;
		this.enrollDate = enrollDate;
	}
	// 3. 메소드
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", nickname=" + nickname + ", userName=" + userName + ", birth=" + birth
				+ ", email=" + email + ", address=" + address + ", petType=" + petType +", petName=" + petName +", levelType=" + levelType +", enrollDate=" + enrollDate + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
	


}