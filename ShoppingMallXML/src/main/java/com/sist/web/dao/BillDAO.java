package com.sist.web.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.sist.web.vo.Bill;


@Repository
public interface BillDAO {
	public int insertBill(Bill b);
	public List<Bill> getBill(String mid);
}
