package org.cita.board.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;


import org.cita.board.dto.ResponseDto;
import org.cita.board.entity.BoardEntity;
import org.cita.board.entity.PopularSearchEntity;
import org.cita.board.repository.BoardRepository;
import org.cita.board.repository.PopularSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired BoardRepository boardRepository;
	@Autowired PopularSearchRepository popularSearchRepository;
	
	public ResponseDto<List<BoardEntity>> getTop3(){
		
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
	
		try {
			boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikesCountDesc(date);
		}catch(Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
	
	return ResponseDto.setSuccess("Success" , boardList);
	}
	
	public ResponseDto<List<BoardEntity>> getList(){
		
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		
		try {
			boardList = boardRepository.findByOrderByBoardWriteDateDesc();
		}catch(Exception exception) {
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("Success" , boardList);
	}
	
	public ResponseDto<List<PopularSearchEntity>> getPopularsearchList(){
		List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();
		
		try {
			popularSearchList = popularSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
		}catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("Success" , popularSearchList);
	}
	
	public ResponseDto<List<BoardEntity>> getSearchList(String boardTitle){
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		
		try {
			boardList = boardRepository.findByBoardTitleContains(boardTitle);
		}catch(Exception exception) {
			exception.printStackTrace();
			return ResponseDto.setFailed("Database Error");
		}
		return ResponseDto.setSuccess("Success" , boardList);
	}
}