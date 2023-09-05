package com.ham.main.member.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ham.main.member.MemberDTO;
import com.ham.main.member.MemberService;
import com.ham.main.product.ProductDTO;
import com.ham.main.product.book.BookDTO;
import com.ham.main.product.book.BookService;
import com.ham.main.product.pay.PayDTO;
import com.ham.main.product.pay.PayService;
import com.ham.main.product.pay.RefundDTO;
import com.ham.main.util.Pager;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PayService payService;
	
  	@GetMapping("onGoing")
	public String getOnGoing(HttpSession session,Model model,Pager pager) throws Exception{
  		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
 
		
  		List<BookDTO> bl = bookService.getBookInfo(memberDTO, pager);
		for(BookDTO b:bl) {
			if(Integer.parseInt(b.getBookCheck())>0) {
				b.setBookCheck("승인");
			}else {
				b.setBookCheck("미승인");
			}
		}
		
        if(bl.size()!=0) {
        	model.addAttribute("list", bl);
        }
       
		return "/mypage/onGoing";
	}
	
  	@GetMapping("complete")
	public String getComplete(HttpSession session,Model model,Pager pager) throws Exception{
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		List<BookDTO> bl = bookService.getBookInfo(memberDTO,pager);
		
		List<PayDTO> pl = new ArrayList<PayDTO>();
		for(BookDTO b: bl) {
			
			PayDTO payDTO = new PayDTO();
			payDTO.setBookNum(b.getBookNum()); 
			payDTO = payService.getPayInfo(payDTO);	
			if(payDTO!=null) {
				System.out.println(payDTO);
				pl.add(payDTO);
			}
		}
		
		if(pl.size()!=0) {
			model.addAttribute("payList", pl);
			model.addAttribute("pager", pager);
		}
		
		return "/mypage/complete";
	}
	 
	@GetMapping("cancle")
	public String getCancle(HttpSession session,Model model,Pager pager) throws Exception{
		 MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
			
		    List<BookDTO> bl = bookService.getBookInfo(memberDTO, pager);  
	    
	    	List<PayDTO> pl = new ArrayList<PayDTO>();
	    	
	    	List<RefundDTO> rl = new ArrayList<RefundDTO>();
			for(BookDTO b: bl) {
				PayDTO payDTO = new PayDTO();
				payDTO.setBookNum(b.getBookNum()); 
				payDTO = payService.getPayInfo(payDTO);	
				if(payDTO!=null) {
				System.out.println(payDTO);
				pl.add(payDTO);
				}
			}
			
			for(PayDTO p:pl) {
				RefundDTO refundDTO = new RefundDTO();
				refundDTO.setPayNum(p.getPayNum());
				refundDTO= payService.getRefundInfo(refundDTO);
				if(refundDTO!=null) {
				rl.add(refundDTO);
				}
			}
			
			if(rl.size()!=0) {
				model.addAttribute("payList", pl);
				model.addAttribute("list", bl);
				model.addAttribute("refundList", rl);
			}
		return "/mypage/cancle";
	}
	
	@GetMapping("productDetail")
	public String getProductDetail() throws Exception {
		
		return "/mypage/productDetail";
	}
	
	@GetMapping("info")
	public String getInfo(MemberDTO memberDTO, HttpSession session) throws Exception{
		memberDTO = (MemberDTO) session.getAttribute("member");

		memberDTO = memberService.getCheckInfo(memberDTO);
		
		session.setAttribute("member", memberDTO);
		
		return "/mypage/info";
	}
	
	@GetMapping("checkInfo")
	public String checkInfo(MemberDTO memberDTO, HttpSession session, Model model) throws Exception {
		memberDTO = (MemberDTO)session.getAttribute("member");
		
		memberDTO = memberService.getCheckInfo(memberDTO);
		
		model.addAttribute("member	", memberDTO);
		
		return "/mypage/checkInfo";
	}
	
	@GetMapping("memberUpdate")
	public String setUpdate() throws Exception {
		
		return "/mypage/memberUpdate";
	}
	@PostMapping("memberUpdate")
	public String setUpdate(MemberDTO memDTO) throws Exception {
		int result = memberService.setUpdate(memDTO);
		
		return "redirect:/mypage/info";
	}
	
	@PostMapping("delete")
	public String setDelete(MemberDTO memDTO, HttpSession session) throws Exception{
		memberService.setDelete(memDTO);
		session.invalidate();
		System.out.println(memDTO.getId());
		
		return "redirect:/";
	}
	
}
