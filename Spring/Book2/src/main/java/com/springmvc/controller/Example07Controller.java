package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/home")
public class Example07Controller {
	
	@GetMapping("/exam07")
	public String requestMethod(ModelMap model) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", "ModelAndView 예제입니다.");
		mav.addObject("data2", "웹 요청 URL은  /home/exam07 입니다. ");
		mav.setViewName("webpage05");
		
		return "mav";		// ModelAndView 객체를 반환하라는 의미
		// 이 객체에 설정된 뷰 이름에 해당하는 뷰를 사용하여 응답을 생성하라는 의미가 됩니다.
	}
}
// setViewName(String viewName) : 반환할 뷰의 이름을 설정합니다.
// addObject(String attributeName, Object attributeValue) : 데이터를 추가
