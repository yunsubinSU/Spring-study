package com.example.app.controller;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test") //url 吏��젙 front controller�뿉�꽌 �뻽�뜕嫄�
public class SimpleController {
	
	@GetMapping("/ex")
	public void ex1_1() throws FileNotFoundException{
		log.info("GET-");
		throw new FileNotFoundException("파일을 찾을 수가 없습니다.");
	}
	
	@RequestMapping( value = "/test1", method=RequestMethod.GET)
		public void test1() {
			//�뙆�씪誘명꽣 
			//�쑀�슚�꽌 
			//�꽌鍮꾩뒪
			//酉곗씠�룞(x)
		}
	
	@RequestMapping( value = "/test2", method=RequestMethod.GET)
		public String test2() {
			log.info("GET /test/test2...");
			return "test/abcd";
		}
	
	@RequestMapping( value = "/test3", method= {RequestMethod.GET,RequestMethod.POST})
		public void test3() {
			log.info("GET /test/test3...");
		}
} 
