package com.example.app.domain.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoServiceImpl {

//	@Autowired
//	private MemoDaoImpl memoDaoIistrationMemo(MemoDto memoDto) throws SQLException {
//		
//		int result =  memoDaoImpl.insert(memoDto);
//		
//		return result>0;
//	}
	
	@Autowired
	private MemoMapper memoMapper;
	
	public boolean registrationMemo(MemoDto memoDto) throws SQLException{
		int result = memoMapper.insert(memoDto);
		
		return result>0;
	}
	
	//전체메모가져오기
	public List<MemoDto> getAllMemo(){
		log.info("MemoService's getAllMemo Call! ");
		return  memoMapper.selectAll(); 
	}
	
	//메모작성 
	@Transactional(rollbackFor = Exception.class) 
	public void addMemoTx(MemoDto dto)	 {
		log.info("MemoService's addMemoTx Call! ");
		int id=dto.getId();
		memoMapper.insert(dto);		//01 정상INSERT 
		dto.setId(id);				//PK오류 발생예정인 dto
		memoMapper.insert(dto);		//02	PK오류 발생!!		
	}
	
	
	
}