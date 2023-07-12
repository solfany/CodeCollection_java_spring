package com.springmvc.ch07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Example04Controller {
   
   @GetMapping("/exam04")
   public String showForm(Model model) {
      //member 라는 이름으로 빈 Member 객체를 추가
      model.addAttribute("member", new Member());
      return "webpage07_01";
   }
//POST 요청이 /exam04 경로로 들어왔을 때 실행되는 핸들러 메소드입니다.
   @PostMapping("/exam04")
   public String submit(@ModelAttribute Member member, Model model) {
//Member 객체를 바인딩하고, 해당 객체를 모델에 추가합니다.
      model.addAttribute("member", member);
      return "webpage07_02";
   }
   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.setAllowedFields("id", "passwd", "city", "sex");
   }
}