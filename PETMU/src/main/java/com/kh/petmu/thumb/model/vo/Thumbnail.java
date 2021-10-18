package com.kh.petmu.thumb.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import com.kh.petmu.freeBoard.model.vo.freeBoard;

public class Thumbnail extends freeBoard implements Serializable {
	
	private static final long serialVersionUID = 9070L;
	
	private ArrayList<Attachment> attachments;
	
	public Thumbnail() { }

	public Thumbnail(int bno, int cateNo, String bwriterId, String bwriterNick, String btitle, String bcontent,
			int bcount, String bfile, int likeCount, Date bdate, String status, int ccount, ArrayList<Attachment> attachments) {
		super(bno, cateNo, bwriterId, bwriterNick, btitle, bcontent, bcount, bfile, likeCount, bdate, status, ccount);
		this.attachments = attachments;
	}

	public Thumbnail(String bwriterId, String bwriterNick, String btitle, String bcontent, String bfile,
			ArrayList<Attachment> attachments) {
		super(bwriterId, bwriterNick, btitle, bcontent, bfile);
		this.attachments = attachments;
	}
	

	@Override
	public String toString() {
		return "Thumbnail [attachments=" + attachments + "]";
	}

	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	
	
	
	
	
	
	

}
