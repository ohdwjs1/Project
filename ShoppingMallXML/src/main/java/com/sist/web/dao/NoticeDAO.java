package com.sist.web.dao;


import java.util.List;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.Notice;
import com.sist.web.vo.Reply;

@Repository
public interface NoticeDAO {

	//�Ǹű��ۼ�
	public int write(Notice notice);
	//��ǰ����Ʈ ���
	public List<Notice> allList(int pg, String field,String query);
	//ī�װ� ��ǰ ����Ʈ ���
	public List<Notice> categorieList(int pg, String field,String query,String categorie);
	public Notice getNotice(String seq);
	public int delNotice(String seq);
	public int updateNotice(Notice n);
	//�� �Խù� �� 
	public int getSeqCount(String field, String query);
	//��۾���
	public int insertReply(Reply r);
	//��� ����Ʈ ��������
	public List<Reply> getReply(String seq);
}
