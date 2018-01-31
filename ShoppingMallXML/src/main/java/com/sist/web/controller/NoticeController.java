package com.sist.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.web.dao.MemberDAO;
import com.sist.web.dao.NoticeDAO;
import com.sist.web.vo.Notice;
import com.sist.web.vo.Reply;


@Controller
@RequestMapping("/customer/*")
public class NoticeController {
	@Autowired
	private NoticeDAO ndao;

	@RequestMapping(value={"notice.do"},method= {RequestMethod.GET,RequestMethod.POST})
	public String notice(String cate, String pg, String f, String q, Model model) throws UnsupportedEncodingException{
		String urlQuery="";
		if(f==null||f.equals("")){
			f="PNAME";
		}
		if(q==null){
			q="";
		}else{
			urlQuery=URLEncoder.encode(q, "UTF-8");
		}
		int seqCount=0;
		if(cate!=null&&!cate.equals("")){
			seqCount=ndao.getSeqCount("CATEGORIE", cate);
		}else{
			seqCount=ndao.getSeqCount(f, q);
		}
		int finalPage=seqCount/5+(seqCount%5==0?0:1);
		if(pg==null||pg.equals("")){
			pg="1";
		}
		int ipg=Integer.parseInt(pg);
		int sPage=((ipg/5)+(ipg%5==0?-1:0))*5+1;
		
		System.out.println(f);
		System.out.println(q);
		ArrayList<Notice> list=null;
		if(cate!=null&&!cate.equals("")){
			System.out.println("ī�װ��˻� : "+cate);
			list=(ArrayList<Notice>)ndao.categorieList(ipg, f, q,cate);
		}else{
			list=(ArrayList<Notice>)ndao.allList(ipg, f, q);
		}
		
		System.out.println("����������"+ipg);
		System.out.println("����������"+sPage);
		
		model.addAttribute("list", list);
		model.addAttribute("sPage", sPage);
		model.addAttribute("finalPage", finalPage);
		model.addAttribute("pg", ipg);
		model.addAttribute("field", f);
		model.addAttribute("query", q);
		model.addAttribute("urlQuery", urlQuery);
		return "notice.jsp";
	}
	@RequestMapping(value={"noticeReg.do"},method=RequestMethod.GET)
	public String noticeReg(HttpServletRequest request){
		String mid=(String)request.getSession().getAttribute("mid");
		if(mid==null||mid==""){
			return "../joinus/login.do";
		}else{
			return "noticeReg.jsp";
		}
	}
	@RequestMapping(value={"noticeReg.do"},method=RequestMethod.POST)
	public String noticeRegProc(HttpServletRequest request, String a) throws IOException{
		String mid=(String)request.getSession().getAttribute("mid");
		if(mid==null||mid==""){
			return "../joinus/login.do";
		}else{
			//�����ּ�(������)�� ������ �����ּҸ� ������ �� �ֵ��� ����
			String path="/customer/upload";
			String realPath=request.getServletContext().getRealPath(path);
			System.out.println("realPath:"+realPath);
			
			//������ ������ ������ ��ġ, �뷮����(10MB), ��������, ������(�ߺ�����)�� �����Ѵ�.
			MultipartRequest mulReq = new MultipartRequest(request, realPath, 10*1024*1024, "UTF-8",new DefaultFileRenamePolicy());
			System.out.println(mulReq.getParameter("pname"));
			//��������������� �̸�
			String fileSrc= mulReq.getFilesystemName("file");
			String orifileSrc= mulReq.getOriginalFileName("file");
			System.out.println("fileSrc : "+fileSrc);
			System.out.println("orifileSrc : "+orifileSrc);
			
			String pname=mulReq.getParameter("pname");
			System.out.println("�����̸�"+pname);
			String content=mulReq.getParameter("content");
			String price=mulReq.getParameter("price");
			String stock=mulReq.getParameter("stock");
			String categorie=mulReq.getParameter("categorie");
			if(price==null||price.equals("")){
				price="0";
			}
			if(stock==null||stock.equals("")){
				stock="0";
			}
			int iprice=Integer.parseInt(price);
			int istock=Integer.parseInt(stock);
			Notice notice = new Notice();
			notice.setPname(pname);
			notice.setContent(content);
			notice.setPrice(iprice);
			notice.setStock(istock);
			notice.setImg(fileSrc);
			notice.setCategorie(categorie);
			System.out.println("��ǰ�̸� : "+notice.getPname());
			int af=ndao.write(notice);
			if(af==1){
				System.out.println("�Ǹű� �ۼ� �Ϸ�");
				return "notice.do";
			}else{
				System.out.println("�Ǹű��ۼ�����");
				return null;
			}
		}
	}
	@RequestMapping(value={"noticeDetail.do"},method=RequestMethod.GET)
	public String noticeDetail(String seq, Model model, HttpServletRequest request){
		//seq=request.getParameter("seq");
		System.out.println("�۹�ȣ  "+seq);
		String urlPath="/customer/upload";
		String realPath = request.getServletContext().getRealPath(urlPath);
		System.out.println("urlPath:"+urlPath);
		System.out.println("realPath:"+realPath);
		
		Notice n=ndao.getNotice(seq);
		model.addAttribute("n",n);
		//model.addAttribute("url", realPath+"\\"+n.getImg());
		//System.out.println("realPath:"+realPath+"\\"+n.getImg());
		//model.addAttribute("mid", request.getSession().getAttribute("mid"));
		System.out.println("������ ����");
		
		List<Reply> list=ndao.getReply(seq);
		
		model.addAttribute("list",list);
		return "noticeDetail.jsp";
	}
	@RequestMapping(value={"noticeModify.do"},method=RequestMethod.GET)
	public String noticeModify(String seq, Model model){
		Notice n=ndao.getNotice(seq);
		ModelAndView mv=new ModelAndView();
		if(n!=null&&!n.equals("")){
			System.out.println("��������������");
			model.addAttribute("n", n);
			return "noticeModify.jsp";
		}else{
			return null;
		}
	}
	@RequestMapping(value={"noticeModify.do"},method=RequestMethod.POST)
	public String noticeModify(String seq, Notice n){
		int af=ndao.updateNotice(n);
		if(af==1){
			System.out.println("�����Ϸ�");
			return "redirect:noticeDetail.do?seq="+seq;
		}else{
			System.out.println("��������");
			return null;
		}
	}
	@RequestMapping(value={"noticeDel.do"},method=RequestMethod.GET)
	public String noticeDel(String seq){
		int af=ndao.delNotice(seq);
		if(af==1){
			System.out.println("�Խñ� �����Ϸ�");
			return "redirect:notice.do";
		}else{
			System.out.println("�Խù���������");
			return null;
		}
	}
	@RequestMapping(value={"reply.do"},method=RequestMethod.GET)
	public String reply(String seq,Model model){
		
		model.addAttribute("seq", seq);
		return "reply.jsp";
	}
	@RequestMapping(value={"reply.do"},method=RequestMethod.POST)
	public String reply(String title, String content, String seq, HttpServletRequest request){
		String mid=(String)request.getSession().getAttribute("mid");

		
		Reply r=new Reply();
		r.setN_seq(seq);
		r.setM_id(mid);
		r.setContent(content);
		System.out.println("�۹�ȣ:"+seq+", ���̵�:"+mid+", ����:"+title+",����:"+content);
		int af=ndao.insertReply(r);

		
		if(af==1){
			System.out.println("����ۼ��Ϸ�");
			return "redirect:noticeDetail.do?seq="+seq;
		}else{
			System.out.println("����");
			return null;
		}
	}
}
