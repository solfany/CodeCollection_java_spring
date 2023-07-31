package org.cita.board.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController{
	
//	@GetMapping("/")
//	public String getBoard() {
//		return "getBoard";
//	}	
//}
	@GetMapping("/")
	public String getBoard(@AuthenticationPrincipal String userEmail) {
		return "로그인된 사용자는 " + userEmail + "입니다.";
	}	
}//포스트맨에서  로그인된 사용자는 citadell@naver.com입니다.   나오게 된다

