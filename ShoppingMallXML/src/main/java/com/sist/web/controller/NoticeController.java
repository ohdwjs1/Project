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
			System.out.println("카테고리검색 : "+cate);
			list=(ArrayList<Notice>)ndao.categorieList(ipg, f, q,cate);
		}else{
			list=(ArrayList<Notice>)ndao.allList(ipg, f, q);
		}
		
		System.out.println("현재페이지"+ipg);
		System.out.println("시작페이지"+sPage);
		
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
			//가상주소(절대경로)를 가지고 물리주소를 가져올 수 있도록 설정
			String path="/customer/upload";
			String realPath=request.getServletContext().getRealPath(path);
			System.out.println("realPath:"+realPath);
			
			//파일의 정보와 저장할 위치, 용량제한(10MB), 문자유형, 리네임(중복방지)를 설정한다.
			MultipartRequest mulReq = new MultipartRequest(request, realPath, 10*1024*1024, "UTF-8",new DefaultFileRenamePolicy());
			System.out.println(mulReq.getParameter("pname"));
			//실제파일이저장된 이름
			String fileSrc= mulReq.getFilesystemName("file");
			String orifileSrc= mulReq.getOriginalFileName("file");
			System.out.println("fileSrc : "+fileSrc);
			System.out.println("orifileSrc : "+orifileSrc);
			
			String pname=mulReq.getParameter("pname");
			System.out.println("제픔이름"+pname);
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
			System.out.println("제품이름 : "+notice.getPname());
			int af=ndao.write(notice);
			if(af==1){
				System.out.println("판매글 작성 완료");
				return "notice.do";
			}else{
				System.out.println("판매글작성실패");
				return null;
			}
		}
	}
	@RequestMapping(value={"noticeDetail.do"},method=RequestMethod.GET)
	public String noticeDetail(String seq, Model model, HttpServletRequest request){
		//seq=request.getParameter("seq");
		System.out.println("글번호  "+seq);
		String urlPath="/customer/upload";
		String realPath = request.getServletContext().getRealPath(urlPath);
		System.out.println("urlPath:"+urlPath);
		System.out.println("realPath:"+realPath);
		
		Notice n=ndao.getNotice(seq);
		model.addAttribute("n",n);
		//model.addAttribute("url", realPath+"\\"+n.getImg());
		//System.out.println("realPath:"+realPath+"\\"+n.getImg());
		//model.addAttribute("mid", request.getSession().getAttribute("mid"));
		System.out.println("디테일 접속");
		
		List<Reply> list=ndao.getReply(seq);
		
		model.addAttribute("list",list);
		return "noticeDetail.jsp";
	}
	@RequestMapping(value={"noticeModify.do"},method=RequestMethod.GET)
	public String noticeModify(String seq, Model model){
		Notice n=ndao.getNotice(seq);
		ModelAndView mv=new ModelAndView();
		if(n!=null&&!n.equals("")){
			System.out.println("수정페이지진입");
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
			System.out.println("수정완료");
			return "redirect:noticeDetail.do?seq="+seq;
		}else{
			System.out.println("수정실패");
			return null;
		}
	}
	@RequestMapping(value={"noticeDel.do"},method=RequestMethod.GET)
	public String noticeDel(String seq){
		int af=ndao.delNotice(seq);
		if(af==1){
			System.out.println("게시글 삭제완료");
			return "redirect:notice.do";
		}else{
			System.out.println("게시물삭제오류");
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
		System.out.println("글번호:"+seq+", 아이디:"+mid+", 제목:"+title+",내용:"+content);
		int af=ndao.insertReply(r);

		
		if(af==1){
			System.out.println("댓글작성완료");
			return "redirect:noticeDetail.do?seq="+seq;
		}else{
			System.out.println("실패");
			return null;
		}
	}
}
