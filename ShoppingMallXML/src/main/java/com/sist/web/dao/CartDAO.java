package com.sist.web.dao;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.Cart;

@Repository
public interface CartDAO {

//ȸ�� ��ٱ��� �Է�
public int insertCart(Cart c,String mid,String isCookie);
//ȸ�� īƮ����Ʈ ���
public List<Cart> getCart(String mid, String isCookie);
//ȸ�� īƮ ����
public int delCart(String cid,String isCookie);

}
