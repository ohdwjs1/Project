package com.sist.web.controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/purchase/*")
public class PurchaseController {
	@Autowired
	MemberDAO mdao;
	@Autowired
	CartDAO cdao;
	@Autowired
	NoticeDAO ndao;
	@Autowired
	BillDAO bdao;




@RequestMapping(value={"order.do"},method= {RequestMethod.GET,RequestMethod.POST})
public String order(String cart, String seq, String pname, String price, String stock, Model model,HttpServletRequest request){
	String mid=(String)request.getSession().getAttribute("mid");

	if(mid==null || mid.equals("")){
		return "redirect:../joinus/login.do";
	}else{
		Member m=null;
		m=mdao.idCheck(mid);
		if(cart!=null&&cart.equals("on")){
			ArrayList<Cart> list=(ArrayList<Cart>)cdao.getCart(mid,"0");
			model.addAttribute("list", list);
			model.addAttribute("m", m);
			model.addAttribute("cart", "on");
			return "order.jsp?cart="+cart;
		}else{
			int iprice=Integer.parseInt(price);
			int istock=Integer.parseInt(stock);				
			Bill b=new Bill(mid, seq, m.getAddress(), iprice, istock, "0");			
			System.out.println("아이디 : "+mid+", seq : "+seq+"주소"+m.getAddress()+"가격"+iprice+"제품이름:"+pname+"수량:"+istock);
			model.addAttribute("m", m);
			model.addAttribute("b", b);
			return "order.jsp";
		}
	}
}
@RequestMapping(value={"orderProc.do"},method=RequestMethod.POST)
public String order(String cart,HttpServletRequest request, String address, String price, String seq, String stock){
	
	String mid=(String)request.getSession().getAttribute("mid");
	int af=0;
	Notice n= null;
	if(mid==null||mid.equals("")) {
		return "redirect:../joinus/login.do";
	}else {
		if(cart!=null&&cart.equals("on")){
			Cart c= new Cart();
			ArrayList<Cart> list=(ArrayList<Cart>)cdao.getCart(mid,"0");
			
			System.out.println("거래할 물량 수 : "+list.size());
			for(int i=0; i<list.size();i++){
				n=ndao.getNotice(list.get(i).getN_seq());
				System.out.println(n.getSeq());
				Bill b=new Bill(mid, n.getSeq(), address, n.getPrice(), list.get(i).getStock(),"0");
				af=af+bdao.insertBill(b);
				mdao.updateMileage(mid,(n.getPrice()*list.get(i).getStock()));
			}
			if(af==(list.size()*2)){
				int af2=cdao.delCart(mid,"0");
				System.out.println("카트 삭제 횟수 : "+af2);
			}
			System.out.println("거래종료 횟수 : "+af);
		}else{
			System.out.println("주문 컨트롤러 진입");
			

			String mid_members=(String)request.getSession().getAttribute("mid");
			int iprice = Integer.parseInt(price);
			int istock = Integer.parseInt(stock);
			
			n=ndao.getNotice(seq);
			
			Bill b=new Bill(mid_members, seq, address, iprice, istock,"0");
			System.out.println("거래내역 생성준비");
			System.out.println("아이디"+mid_members+",거래번호"+seq+",주소"+address+",가격"+iprice+",수량"+istock+",판매자");
			af=bdao.insertBill(b);
			System.out.println("거래종료 횟수 : "+af);
			if(af==1){
				System.out.println("주문완료");
				return "orderCompletion.jsp";
			}else{
				System.out.println("주문실패");
				return null;
				
			}
		}
		
		if(af>=2){
			System.out.println("주문완료");
			return "orderCompletion.jsp";
		}else{
			System.out.println("주문실패");
			return null;
			
		}
	}
}
@RequestMapping(value={"cart.do"},method=RequestMethod.GET)
public String cart(HttpServletRequest request, Model model){
	String mid=(String)request.getSession().getAttribute("mid");


	if(mid!=null&&!mid.equals("")){
		ArrayList<Cart> list=(ArrayList<Cart>)cdao.getCart(mid,"0");
		System.out.println("회원장바구니목록갯수:"+list.size());
		model.addAttribute("list", list);
	}else{
		Cookie[] coos = request.getCookies();
		String cid=CookieManager.getCookie(coos, "cid");
		if(cid!=null&&!cid.equals("")){
			ArrayList<Cart> list=(ArrayList<Cart>)cdao.getCart(cid,"1");
			System.out.println("쿠키 : "+cid+", 비회원장바구니 갯수"+list.size());
			model.addAttribute("list", list);

		}
	}
	
	return "cart.jsp";
}
@RequestMapping(value={"cart.do"},method=RequestMethod.POST)
public String cart(String pname, String seq, String stock, String price, HttpServletRequest request, HttpServletResponse response){

	//장바구니등록 시 회원, 비회원을 따로 등록
	String mid=(String)request.getSession().getAttribute("mid");
	ModelAndView mv= new ModelAndView();
	//스트링값 을 인트로 변환전 유효성검사
	int istock=0;
	int iprice=0;
	if(stock!=null||!stock.equals("")){
		istock=Integer.parseInt(stock);
	}
	if(price!=null||!price.equals("")){
		iprice=Integer.parseInt(price);
	}
	if(mid!=null&&!mid.equals("")){

		Cart c=new Cart(istock, seq, mid, "0");
		cdao.insertCart(c, mid,"0");
		
		return "redirect:cart.do";
	}else{
		Cookie[] coos = request.getCookies();
		String cid=CookieManager.getCookie(coos, "cid");
		if(cid!=null&&!cid.equals("")){
			System.out.println("쿠키 존재 : "+cid);
			Cart c2=new Cart(istock, seq, cid, "1");

			int af=cdao.insertCart(c2,cid,"1");
			if(af==1){
				System.out.println("장바구니 넣기 완료");
				return "redirect:cart.do";
			}else{
				System.out.println("장바구니 넣기 실패");
				return null;
			}
		}else{
			System.out.println("첫 방문 감사합니다");
			int start = (int)(Math.random()*27);
			String rnum=UUID.randomUUID().toString().replace("-", "").substring(start, start+10);
			Cookie ck = new Cookie("cid",rnum);
			ck.setMaxAge(60*60*24*30);
			ck.setPath("/");
			System.out.println("비회원 고유번호 : "+rnum);
			response.addCookie(ck);
			
			Cart c2=new Cart(istock, seq, rnum, "1");

			int af=cdao.insertCart(c2,rnum,"1");
			if(af==1){
				System.out.println("장바구니 넣기 완료");
				return "redirect:cart.do";
			}else{
				System.out.println("장바구니 넣기 실패");
				return null;
			}
		}
	}
}

}
