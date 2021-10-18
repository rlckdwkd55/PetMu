package com.kh.petmu.comment.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable{

	private static final long serialVersionUID = 1006L;
	
	//comment 변수명
	private int cno;
	private int bno;
	private String cwriterId;
	private String cwriterNick;
	private String ccontent;
	private Date cdate;
	private String status;
	
	public Comment() {}

	public Comment(int cno, int bno, String cwriterId, String cwriterNick, String ccontent, Date cdate, String status) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.cwriterId = cwriterId;
		this.cwriterNick = cwriterNick;
		this.ccontent = ccontent;
		this.cdate = cdate;
		this.status = status;
	}

	public Comment(int cno, String cwriterNick, String ccontent) {
		super();
		this.cno = cno;
		this.cwriterNick = cwriterNick;
		this.ccontent = ccontent;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getCwriterId() {
		return cwriterId;
	}

	public void setCwriterId(String cwriterId) {
		this.cwriterId = cwriterId;
	}

	public String getCwriterNick() {
		return cwriterNick;
	}

	public void setCwriterNick(String cwriterNick) {
		this.cwriterNick = cwriterNick;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Comment [cno=" + cno + ", bno=" + bno + ", cwriterId=" + cwriterId + ", cwriterNick=" + cwriterNick
				+ ", ccontent=" + ccontent + ", cdate=" + cdate + ", status=" + status + "]";
	}

	
	
	
	
	
}
