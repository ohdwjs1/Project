package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sist.web.vo.Notice;
import com.sist.web.vo.Reply;

public class NoticeDAOService implements NoticeDAO{
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int write(Notice notice) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.write(notice);
	}

	@Override
	public List<Notice> allList(int pg, String field, String query) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.allList(pg, field, query);
	}

	@Override
	public List<Notice> categorieList(int pg, String field, String query, String categorie) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.categorieList(pg, field, query, categorie);
	}

	@Override
	public Notice getNotice(String seq) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.getNotice(seq);
	}

	@Override
	public int delNotice(String seq) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.delNotice(seq);
	}

	@Override
	public int updateNotice(Notice n) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.updateNotice(n);
	}

	@Override
	public int getSeqCount(String field, String query) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.getSeqCount(field, query);
	}

	@Override
	public int insertReply(Reply r) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.insertReply(r);
	}

	@Override
	public List<Reply> getReply(String seq) {
		NoticeDAO ndao=sqlSession.getMapper(NoticeDAO.class);
		return ndao.getReply(seq);
	}

}
