package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class Example01Controller {
	
	@RequestMapping(value="/exam01/{bookId}")
	public String requestMethod(@PathVariable String bookId, Model model) {
		System.out.println("도서 ID : " + bookId);
		model.addAttribute("data", "도서 ID : " + bookId);
		return "webpage06";
	}
}
// 경로 변수 (path variables)
// 웹 요청 URL에 포함된 파라미터 값을 전달받는데 사용하는 변수
// 이때 매핑 경로를 설정하는 @RequestMapping에 중괄호({ })를 사용하여
// 웹 요청 URL에 포함된 요청 조건 값을 전달받음
// 중괄호 안에 명시된 것이 경로 변수이며, 하나 또는 그 이상의 다중경로 변수를 포함할 수 있음.