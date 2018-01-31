package com.sist.web.dao;

import org.apache.ibatis.session.SqlSession;

import com.sist.web.vo.Member;

public class MemberDAOService implements MemberDAO{
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int join(Member m) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.join(m);
	}

	@Override
	public Member idCheck(String mid) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.idCheck(mid);
	}

	@Override
	public int withdraw(String mid) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.withdraw(mid);
	}

	@Override
	public int update(Member m) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.update(m);
	}

	@Override
	public Member idFind(Member m) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.idFind(m);
	}

	@Override
	public Member pwdFind(Member m) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.pwdFind(m);
	}

	@Override
	public int updateMileage(String mid, int i) {
		MemberDAO mdao=sqlSession.getMapper(MemberDAO.class);
		return mdao.updateMileage(mid, i);
	}

}
