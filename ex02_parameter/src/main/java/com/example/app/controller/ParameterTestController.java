package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.app.domain.dto.PersonDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/param")
public class ParameterTestController {

	@RequestMapping(value="/p01", method=RequestMethod.GET)
	
	public void p01(@RequestParam(value="name",required=false)String name) {
		log.info("GET /param/p01.... " + name);
	}
	
	@GetMapping("/p02")
	public void p02(@RequestParam(value="name", required=true) String name) {
		log.info("GET /param/p02..." + name);
	}
	
	@PostMapping("/p03")
	public void p03(@RequestParam(value="name", required=true) String name) {
		log.info("POST /param/p03..." + name);
	}
	
	//@RequestParam: 동기요청 파라미터 처리 / form기반 전달되는 파라미터 받기
	//@RequestBody : 비동기요청 파라미터 처리 / form /json 등 파라미터 받기
	@PostMapping("/p04")
	public void p04(@RequestBody String name) {
		log.info("POST /param/p04..." + name);
	}
	
	@RequestMapping(value="/p05", method=RequestMethod.GET)
	public void p05(@RequestParam(value="name",defaultValue="홍길동") String name) {
		log.info("GET /param/p05..." + name);
	}
	
	@RequestMapping(value="/p07",method=RequestMethod.GET)
	public void p07(@ModelAttribute PersonDto dto)
	{
		log.info("GET /param/p07..." + dto);
	}
	
	@RequestMapping(value="/p08/{username}/{age}/{addr}",method=RequestMethod.GET)
	public void p08(
				@PathVariable("username") String username,
				@PathVariable int age,
				@PathVariable String addr
			)
	{
		log.info("GET /param/p08..." + username+" " +age + " " + addr);
	}
	
	@RequestMapping(value="/p09/{username}/{age}/{addr}",method=RequestMethod.GET)
	public void p09(@ModelAttribute PersonDto dto)
	{
		log.info("GET /param/p09..." + dto);
	}
	
	@GetMapping("/page01")
	public void page01(PersonDto dto,Model model) {
		log.info("GET /param/page01..." + dto);
		//반환자료형이 void일때 / WEB-INF / views / param / page01.jsp 와 매핑
		
		//파라미터
		//유효성
		//서비스
		//뷰이동 + 데이터 전달(Model 객체 - DispatherServlet(FC))
		model.addAttribute("dto",dto);
		model.addAttribute("test1","test1Value");
		
	}
	
	@GetMapping("/page02")
	public String page02(PersonDto dto,Model model) {
		log.info("GET /param/page02..." + dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test2","test2Value");
		
		//반환자료형이 void : /WEB-INF/views/param/page02.jsp 와 매핑
		//위치 변경시 String + return "path"
		
		return "param/page01";
		
	}
	
	@GetMapping("/page03/{username}/{age}/{addr}")
	public String page03(PersonDto dto,Model model) {
		log.info("GET /param/page03..." + dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test3","test3Value");
		return "param/page01";
		
	}
	
	@GetMapping("/page04/{username}/{age}/{addr}")
	public ModelAndView page04(PersonDto dto) {
		log.info("GET /param/page04..." + dto);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("dto",dto);
		modelAndView.setViewName("param/page01");
		
		return modelAndView;
		
		
		
	}
	
	@GetMapping("/page05/{username}/{age}/{addr}")
	public ModelAndView page05(PersonDto dto) {
		log.info("GET /param/page04..." + dto);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("dto",dto);
		modelAndView.setViewName("param/page01");
		
		return modelAndView;
		
	}
	
	@GetMapping("/redirect1")
	public String r1(Model mode, ResirectAttribute  redirectAttributes) {
		log.info("/param/redirect1");
		redirectAttributes.addAttribute("r1","r1Value"); //쿼리스트링
		return "redirect:/param/redirect2";
	}
	
	
	
	
}
