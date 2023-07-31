package org.cita.board.controller;

import java.util.List;

import org.cita.board.dto.ResponseDto;
import org.cita.board.entity.BoardEntity;
import org.cita.board.entity.PopularSearchEntity;
import org.cita.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController{
	
	@Autowired BoardService boardService;
	
	@GetMapping("/top3")
	public ResponseDto<List<BoardEntity>> getTop3(){
		return boardService.getTop3();
	}
	
	@GetMapping("/list")
	public ResponseDto<List<BoardEntity>> getList(){
		return boardService.getList();
	}
	
	@GetMapping("/popularsearchList")
	public ResponseDto<List<PopularSearchEntity>> getPopularsearchList(){
		return boardService.getPopularsearchList();
	}
	
//	@GetMapping("/")
//	public String getBoard() {
//		return "getBoard";
//	}	
//}
//	@GetMapping("/")
//	public String getBoard(@AuthenticationPrincipal String userEmail) {
//		return "로그인된 사용자는 " + userEmail + "입니다.";
//	}	
}//포스트맨에서  로그인된 사용자는 citadell@naver.com입니다.   나오게 된다

