package com.sist.web.dao;



import org.springframework.stereotype.Repository;

import com.sist.web.vo.Member;


@Repository
public interface MemberDAO {
	//member join
	public int join(Member m);
	public Member idCheck(String mid);
	public int withdraw(String mid);
	public int update(Member m);
	//아이디찾기
	public Member idFind(Member m);
	public Member pwdFind(Member m);
	public int updateMileage(String mid, int i);
}
