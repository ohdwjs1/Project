package com.sist.web.vo;

public class Notice {
	private String seq;
	private String pname;
	private String content;
	private String img;
	private int price;
	private String regdate;
	private String categorie;
	private int stock;
	
	 
	public Notice() {
		super();
	}
	public Notice(String seq, String pname, String content, String img, int price, String regdate, String categorie,
			int stock) {
		super();
		this.seq = seq;
		this.pname = pname;
		this.content = content;
		this.img = img;
		this.price = price;
		this.regdate = regdate;
		this.categorie = categorie;
		this.stock = stock;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	
}
