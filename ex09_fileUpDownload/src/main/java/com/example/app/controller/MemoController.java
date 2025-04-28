package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoServiceImpl memoServiceImpl;
	
	
//	@InitBinder
//	public void dataBinder(WebDataBinder webDataBinder) {
//		log.info("MemoController's dataBinder ..." + webDataBinder);
//		webDataBinder.registerCustomEditor(LocalDate.class, "dateTest", new DateTestEditor());
//	}
	
	
	@GetMapping("/ex")
	public void ex1_1() throws FileNotFoundException{
		log.info("GET -");
		throw new FileNotFoundException("파일을 찾을수가 없습니다.");
	}
	
	
	
	@GetMapping("/add")
	public void add_get(MemoDto dto) throws SQLException {
		log.info("GET /memo/add...");
		memoServiceImpl.registrationMemo(dto);
	}
	
	@PostMapping("/add")
	public void add_post(@Valid MemoDto dto,BindingResult bindingResult,Model model) throws Exception {
		log.info("POST /memo/add..."+dto);
		
		if(bindingResult.hasErrors()) {
//			log.info("유효성 에러발생 : " + bindingResult.getFieldError("id").getDefaultMessage());
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				log.info("Error Field : "+error.getField()+" Error Msg : "+error.getDefaultMessage());
				model.addAttribute(error.getField(),error.getDefaultMessage());
			}
			
			return ;
		}
		//서비스
		boolean isAdded =  memoServiceImpl.registrationMemo(dto);
		//memoServiceImpl.addMemoTx(dto);
		
		
	}
	
	// static private 	
	private static class DateTestEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String dateTest) throws IllegalArgumentException {
			log.info("DateTestEditor's setAsText invoke.." + dateTest);
			LocalDate date = null;
			if(dateTest.isEmpty()) {
				date = LocalDate.now();
			}else {
				//yyyy#MM#dd -> yyyy-MM-dd(LocalDate format)
				dateTest = dateTest.replaceAll("#", "-");
				date = LocalDate.parse(dateTest,DateTimeFormatter.ofPattern("yyyy-MM-dd"));		
			}
			
			setValue(date);
			
			
		}	
	}
	//
	@GetMapping("/rest")
	public void rest() {
		log.info("GET /rest...");
	}
	

}

