package com.ham.main.partner;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ham.main.member.MemberDTO;
import com.ham.main.member.MemberService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/partner/*")
public class PartnerController {

	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private MemberService memberService;
	
	private final String SERVICE_KEY = "bJ6kMWyyBbwha2z3sjC5XeliD%2F%2FTMdJcXmr9veQc%2BIKmwfUs0IYcoRmG0F7qXaxYkf61qRvzBy67J30mnS64Mg%3D%3D";
	
	
	
	//로그인
	@GetMapping("partnerLogin")
	public void getMemberLogin() throws Exception {
		
	}
	
	@SuppressWarnings("unused")
	@PostMapping("partnerLogin")
	public ModelAndView getMemberLogin(MemberDTO memberDTO, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println(memberDTO);
		memberDTO = memberService.getMemberLogin(memberDTO);
		System.out.println(memberDTO);
		PartnerDTO partnerDTO = partnerService.getPartnerInfo(memberDTO.getId());
		
		System.out.println(memberDTO);
		System.out.println(partnerDTO);
		if(memberDTO != null) {
			session.setAttribute("member", memberDTO);
			session.setAttribute("partner", partnerDTO);
			mv.setViewName("redirect:../");
		}else {
			mv.addObject("errorMessage", "로그인에 실패했습니다.");
			mv.setViewName("/partner/partnerLogin");
		}
		
		return mv;
	}
	
	//사업자등록
	@GetMapping("partnerRegister")
	public ModelAndView setPartnerRegisterJoin() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("serviceKey", SERVICE_KEY);
		mv.setViewName("/partner/partnerRegister");
		
		return mv;
	}
	
	@PostMapping("partnerRegister")
	public ModelAndView setPartnerRegisterJoin(PartnerDTO partnerDTO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = partnerService.setPartnerRegisterJoin(partnerDTO);
		mv.setViewName("redirect:../");
		
		return mv;
	}



	@GetMapping("list")
	public String getList(PartnerDTO partnerDTO,Model model) throws Exception{
		List<PartnerDTO> partnerList = partnerService.getList(partnerDTO);
		model.addAttribute("list", partnerList);
		
		return "admin/partnerList";
	}
	
	@PostMapping("update")
	public String setPermitUpdate(PartnerDTO partnerDTO,Model model) throws Exception{
		int result = partnerService.setPermitUpdate(partnerDTO);
		model.addAttribute("result", result);
		
		return "commons/ajaxResult";
	}
	
	@GetMapping("detail")
	public String getDetail(PartnerDTO partnerDTO,Model model) throws Exception{
		partnerDTO = partnerService.getDetail(partnerDTO);
		model.addAttribute("kto", partnerDTO);
		
		return "admin/partnerDetail";
	}
	
	
	

}
