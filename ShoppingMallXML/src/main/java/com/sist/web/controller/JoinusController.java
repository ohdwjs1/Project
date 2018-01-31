package com.sist.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sist.web.dao.BillDAO;
import com.sist.web.dao.CartDAO;
import com.sist.web.dao.MemberDAO;
import com.sist.web.dao.NoticeDAO;
import com.sist.web.util.CookieManager;
import com.sist.web.vo.Bill;
import com.sist.web.vo.Cart;
import com.sist.web.vo.Member;
import com.sist.web.vo.Notice;


@Controller
@RequestMapping("/joinus/*")
public class JoinusController {
	@Autowired
	private NoticeDAO ndao;
	@Autowired
	private MemberDAO mdao;
	@Autowired
	private CartDAO cdao;
	@Autowired
	private BillDAO bdao;



	@RequestMapping(value={"index.do"},method=RequestMethod.GET)
	public String index(String pg, String f, String q, HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		Cookie[] coos = request.getCookies();
		String cid=CookieManager.getCookie(coos, "cid");
		System.out.println("��Ű ���� : "+cid);
		
		request.setCharacterEncoding("UTF-8");
		String mid=(String)request.getSession().getAttribute("mid");
		String name=(String)request.getSession().getAttribute("name");

		String urlQuery="";

		
		if(f==null||f.equals("")){
			f="PNAME";
		}
		if(q==null){
			q="";
		}else{
			urlQuery=URLEncoder.encode(q, "UTF-8");
		}
		int seqCount=ndao.getSeqCount(f, q);
		int finalPage=seqCount/5+(seqCount%5==0?0:1);
		if(pg==null||pg.equals("")){
			pg="1";
		}
		int ipg=Integer.parseInt(pg);
		int sPage=((ipg/5)+(ipg%5==0?-1:0))*5+1;
		
		System.out.println(f);
		System.out.println(q);
		ArrayList<Notice> list=(ArrayList<Notice>)ndao.allList(ipg, f, q);
		System.out.println("����������"+ipg);
		System.out.println("����������"+sPage);
		
		model.addAttribute("list", list);
		model.addAttribute("sPage", sPage);
		model.addAttribute("finalPage", finalPage);
		model.addAttribute("pg", ipg);
		model.addAttribute("field", f);
		model.addAttribute("query", q);
		model.addAttribute("urlQuery", urlQuery);
		model.addAttribute("mid",mid);
		model.addAttribute("name", name);
		return "index.jsp";
	}
	@RequestMapping(value={"join.do"},method=RequestMethod.GET)
	public String join(){
		return "join.jsp";
	}
	@RequestMapping(value={"join.do"},method=RequestMethod.POST)
	public String join(Member m,HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		//join
		int af=mdao.join(m);
		if(af==1){
			System.out.println("ȸ�����Լ���");
			return "redirect:index.do";
		}else{
			return null;
		}
		
	}
	@RequestMapping(value={"login.do"},method=RequestMethod.GET)
	public String login(String error, Model model){

		if(error!=null&&!(error.equals(""))){
			if(error.equals("IDx")){
				error="���̵� ��ġ���� �ʽ��ϴ�.";
			}else if(error.equals("PWDx")){
				error="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			}
		}
		model.addAttribute("error", error);
		return "login.jsp";		
	}
	@RequestMapping(value={"login.do"},method=RequestMethod.POST)
	public String login(String mid, String pwd, HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		Member m=mdao.idCheck(mid);
		
		if(m==null){
			System.out.println("���̵� �������� �ʽ��ϴ�.");

			return "redirect:login.do?error=IDx";
		}else if(m!=null && !(m.getPwd().equals(pwd))){
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");

			return "redirect:login.do?error=PWDx";
		}else{
			System.out.println("�α��� ����!");
			request.getSession().setAttribute("mid", mid);
			request.getSession().setAttribute("name", m.getName());
			//�α��� �� ��Ű�� �ִ� ��ٱ��� ��� ȸ����ٱ��Ϸ� �̵�
			Cookie[] coos = request.getCookies();
			String cid=CookieManager.getCookie(coos, "cid");
			System.out.println("���� ��Ű : "+cid);
			//��Ű�� ������ ���, ��Ű��ٱ��� �ҷ�����
			if(cid!=null&&!cid.equals("")){
				ArrayList<Cart> list=(ArrayList<Cart>)cdao.getCart(cid,"1");
				int af=0;
				//�α��� ��, ��Ű��ٱ��ϸ� �α��� �� id�� �����̵�
				if(list!=null){
					for(int i=0; i<list.size();i++){
						Cart temp=list.get(i);
						af=af+cdao.insertCart(temp,mid,"0");
					}
				}
				System.out.println(af+"�� ��ٱ��� �̵� �Ϸ�");
				//�̵� �� ���� ��ȸ�� ��ٱ��� ����
				int af2=cdao.delCart(cid,"1");
				System.out.println(af2+"�� �ӽ���ٱ��� ���� �Ϸ�");
				
			}
			
			return "redirect:index.do";
		}
	}
	@RequestMapping(value={"logout.do"},method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("mid");
		request.getSession().removeAttribute("name");
		return "redirect:index.do";
	}
	@RequestMapping(value={"mypage.do"},method=RequestMethod.GET)
	public String mypage(HttpServletRequest request,Model model){
		String mid=(String)request.getSession().getAttribute("mid");

		if(mid==null||mid==""){
			return "../joinus/login.do";
			
		}else{
		System.out.println("�������������� ID : "+mid);

		Member m=mdao.idCheck(mid);
		model.addAttribute("m", m);
		
		return "mypage.jsp";
		}
	}
	@RequestMapping(value={"myinfo.do"},method=RequestMethod.GET)
	public String myinfo(HttpServletRequest request, Model model){
		String mid=(String)request.getSession().getAttribute("mid");
		if(mid==null||mid==""){
			return "redirect:../joinus/login.do";
		}else{

			ArrayList<Bill> list=(ArrayList<Bill>)bdao.getBill(mid);
			Member m=null;
			System.out.println("���� Id : "+mid);
			
			m=mdao.idCheck(mid);

			model.addAttribute("m", m);
			model.addAttribute("list", list);
			
			return "myinfo.jsp";
			
		}
	}
	@RequestMapping(value={"withdraw.do"},method=RequestMethod.GET)
	public String withdraw(){
		return "withdraw.jsp";
	}
	@RequestMapping(value={"withdrawProc.do"},method=RequestMethod.GET)
	public String withdrawProc(HttpServletRequest request){
		int af=mdao.withdraw((String)request.getSession().getAttribute("mid"));
		ModelAndView mv=new ModelAndView();
		if(af==1){
			System.out.println("����Ż�� �Ϸ�");
			request.getSession().removeAttribute("mid");
			request.getSession().removeAttribute("name");
			
			return "withdrawComplete.jsp";
		}else{
			System.out.println("Ż�����");
			return null;
		}
	}
	@RequestMapping(value={"mypage.do"},method=RequestMethod.POST)
	public String mypage(String email, String phone, String address, HttpServletRequest request){
		System.out.println("ȸ����������������");


		Member m=new Member();
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setMid((String)request.getSession().getAttribute("mid"));
		int af=mdao.update(m);
		if(af==1){
			System.out.println("���������Ϸ�");

			return "redirect:mypage.do";
		}else{
			return null;
			
		}
	}
	@RequestMapping(value={"findId.do"},method=RequestMethod.GET)
	public String findId(){
		return "findId.jsp";
	}
	@RequestMapping(value={"findId.do"},method=RequestMethod.POST)
	@ResponseBody
	public String findId(String name, String email, Model model, HttpServletRequest request) throws UnsupportedEncodingException{
		System.out.println("FindId_resultController");
		request.setCharacterEncoding("UTF-8");

		Member m=new Member();
		m.setName(name);
		m.setEmail(email);
		Member result=mdao.idFind(m);
		String val="";
		if(result==null||result.equals("")){
			System.out.println("���̵� ã�� ����");
			return val;
		}else{
			System.out.println("���̵� ã�� ����");
/*			model.addAttribute("member", result);
			return "findId_result.jsp";*/
			Gson gson=new Gson();
			val= gson.toJson(result);
			System.out.println(val);
			return val;
		}
	}
	@RequestMapping(value={"findPwd.do"},method=RequestMethod.GET)
	public String findPwd(){
		return "findPwd.jsp";
	}
	@RequestMapping(value={"findPwd.do"},method=RequestMethod.POST)
	@ResponseBody
	public String findPwd(String mid, String name, String email, Model model, HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");

		Member m=new Member();
		m.setMid(mid);
		m.setName(name);
		m.setEmail(email);
		Member result=mdao.pwdFind(m);
		String val="";
		if(result==null||result.equals("")){
			System.out.println("��������� �����ʽ��ϴ�.");
			return val;
		}else{
			System.out.println("��й�ȣ ã��Ϸ�");
			Gson gson = new Gson();
			val=gson.toJson(result);
			System.out.println(val);
			return val;
		}
	}
}
