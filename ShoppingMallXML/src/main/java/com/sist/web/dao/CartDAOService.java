package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sist.web.vo.Cart;

public class CartDAOService implements CartDAO{
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertCart(Cart c, String mid, String isCookie) {
		CartDAO cdao=sqlSession.getMapper(CartDAO.class);
		return cdao.insertCart(c, mid, isCookie);
	}

	@Override
	public List<Cart> getCart(String mid, String isCookie) {
		CartDAO cdao=sqlSession.getMapper(CartDAO.class);
		return cdao.getCart(mid, isCookie);
	}

	@Override
	public int delCart(String cid, String isCookie) {
		CartDAO cdao=sqlSession.getMapper(CartDAO.class);
		return cdao.delCart(cid, isCookie);
	}

}
