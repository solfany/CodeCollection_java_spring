package com.springmvc.ch07;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Example01Controller {
   @GetMapping("/member")
//"/member" 엔드포인트에 요청이 올 경우 해당 메소드가 실행되도록 설정하는 역할을 합니다
// 엔드포인트는 주로 폼 데이터를 보여주는 역할
   public String showForm(Model model) {
      Member member = new Member();
      System.out.println("@GetMapping-------------");
      System.out.println("아이디: " + member.getId());
      System.out.println("비밀번호: " + member.getPasswd());
      System.out.println("거주지: " + member.getCity());
      System.out.println("성 별: " + member.getSex());
      System.out.println("취 미: " + member.getHobby());
      model.addAttribute("member", member);
      return "webpage07_01";
   }// 엔드포인트(endpoint)는 URL의 일부로, 서버가 리소스를 제공하는 정확한 위치
}//주로 "/"로 시작하는 경로(path) 부분을 의미