package com.sist.web.vo;

public class Bill {
	private String seq;
	private String m_id;
	private String n_seq;
	private String address;
	private int price;
	private int stock;
	private String status;				
	private String dealdate;
	
	
	public Bill() {
		super();
	}
	public Bill(String m_id, String n_seq, String address, int price, int stock, String status) {
		super();
		this.m_id = m_id;
		this.n_seq = n_seq;
		this.address = address;
		this.price = price;
		this.stock = stock;
		this.status = status;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getN_seq() {
		return n_seq;
	}
	public void setN_seq(String n_seq) {
		this.n_seq = n_seq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDealdate() {
		return dealdate;
	}
	public void setDealdate(String dealdate) {
		this.dealdate = dealdate;
	}
	
	
}


