package com.sist.web.dao;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.Notice;
import com.sist.web.vo.Reply;

@Repository
public interface NoticeDAO {

	//판매글작성
	public int write(Notice notice);
	//상품리스트 출력
	public List<Notice> allList(int pg, String field,String query);
	//카테고리 상품 리스트 출력
	public List<Notice> categorieList(int pg, String field,String query,String categorie);
	public Notice getNotice(String seq);
	public int delNotice(String seq);
	public int updateNotice(Notice n);
	//총 게시물 수 
	public int getSeqCount(String field, String query);
	//댓글쓰기
	public int insertReply(Reply r);
	//댓글 리스트 가져오기
	public List<Reply> getReply(String seq);
}
