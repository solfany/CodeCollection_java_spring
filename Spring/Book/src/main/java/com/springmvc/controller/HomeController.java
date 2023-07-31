package com.springmvc.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
// 프레임워크(Framework) 점의
// 사전적 의미는 어떤 것을 구성하는 구조 또는 뼈대
// 소프트웨어적 의미로는 ‘기능을 미리 클래스나 인터페이스 등으로 만들어 제공하는 반제품’
// 스프링의 특징
// EJB보다 가볍고 배우기도 쉬우며 경량 컨테이너의 기능을 수행
// '제어 역행(IoC, Inversion of Control) 기술을 이용해 애플리케이션 간의 느슨한 결합을
// '의존성 주입(DI, Dependency Injection) 기능을 지원함 관점 지향 (AOP, Aspect-oriented Programming) 기능을 이용해 자원 관리함
// '영속성과 관련된 다양한 서비스를 지원함
// '수많은 라이브러리와의 연동 기능을 지원함