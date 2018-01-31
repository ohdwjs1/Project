package com.sist.web.dao;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.Cart;

@Repository
public interface CartDAO {

//회원 장바구니 입력
public int insertCart(Cart c,String mid,String isCookie);
//회원 카트리스트 출력
public List<Cart> getCart(String mid, String isCookie);
//회원 카트 삭제
public int delCart(String cid,String isCookie);

}
