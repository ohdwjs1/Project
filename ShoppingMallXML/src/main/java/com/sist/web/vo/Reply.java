package com.sist.web.vo;

public class Reply {
	private String seq;
	private String n_seq;
	private String img;
	private String m_id;
	private String content;
	private String regdate;
	
	public Reply() {
		super();
	}
	public Reply(String seq, String n_seq, String img, String m_id, String content, String regdate) {
		super();
		this.seq = seq;
		this.n_seq = n_seq;
		this.img = img;
		this.m_id = m_id;
		this.content = content;
		this.regdate = regdate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getN_seq() {
		return n_seq;
	}
	public void setN_seq(String n_seq) {
		this.n_seq = n_seq;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
