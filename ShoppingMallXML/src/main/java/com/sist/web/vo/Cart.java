package com.sist.web.vo;

public class Cart {
	private String seq;
	private int stock;
	private String regdate;
	private String n_seq;
	private String id;
	private String isCookie;
	public Cart() {
		
	}
	
	public Cart(int stock, String n_seq, String id, String isCookie) {
		this.stock = stock;
		this.n_seq = n_seq;
		this.id = id;
		this.isCookie = isCookie;
	}

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getN_seq() {
		return n_seq;
	}
	public void setN_seq(String n_seq) {
		this.n_seq = n_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsCookie() {
		return isCookie;
	}
	public void setIsCookie(String isCookie) {
		this.isCookie = isCookie;
	}
	
	
}
