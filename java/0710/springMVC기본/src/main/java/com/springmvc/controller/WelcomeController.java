package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//Spring MVC에서 컨트롤러 역할을 하는 클래스임 
public class WelcomeController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	
	//"home" URL에 대한 GET 요청을 처리 
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to BookMarket");
	
	//Model 객체에 "strpline" 이라는 이름의 속성을 추가하고, 그 값을 
		model.addAttribute("strapline", "Welcome to Web Shopping Mall!");
		return "welcome";
	}
}

// 빌드 정보 : 프로젝트를 빌드할 때 필요한 요소들을 불러오고 싶다면 요소안에 설정 
// 플러그인:  요소를 이용하여 빌드에서 사용할 플러그인 설정 