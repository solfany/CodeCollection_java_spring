package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

public class Example03Controller {
	
	@RequestMapping(value="/exam03")
	public void requestMethod() {
		System.out.println("@RequestMapping 예제입니다.");
		System.out.println("웹 요청 URL은 /exam03 입니다.");
	}
}
// M : Modified. 소스 코드 파일이 수정되었지만 아직 커밋되지 않은 상태를 나타냅니다.
// S : Staged. 수정된 파일이 커밋을 위해 준비되었음을 나타냅니다.
// ? : 버전 관리 시스템(Git 등)이 해당 파일을 추적하고 있지 않음을 나타냅니다.