package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class Example06Controller {
	
	@GetMapping("/exam06")
	public String requestMethod(ModelMap model) {
		model.addAttribute("data", "ModelMap 예제입니다.");
		model.addAttribute("data2", "웹 요청 URL은 /home/exam06 입니다.");
		return "webpage05";
	}
}

// M : Modified. 소스 코드 파일이 수정되었지만 아직 커밋되지 않은 상태를 나타냅니다.
// S : Staged. 수정된 파일이 커밋을 위해 준비되었음을 나타냅니다.
// ? : 버전 관리 시스템(Git 등)이 해당 파일을 추적하고 있지 않음을 나타냅니다.