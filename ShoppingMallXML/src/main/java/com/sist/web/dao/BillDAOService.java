package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sist.web.vo.Bill;

public class BillDAOService implements BillDAO{
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertBill(Bill b) {
		BillDAO bdao=sqlSession.getMapper(BillDAO.class);
		return bdao.insertBill(b);
	}

	@Override
	public List<Bill> getBill(String mid) {
		BillDAO bdao=sqlSession.getMapper(BillDAO.class);
		return bdao.getBill(mid);
	}

}
