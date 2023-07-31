package com.springmvc.ch07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Example03Controller {
   @GetMapping("/exam03")
   public String showForm() {
      return "webpage07_03";
   }
   @ModelAttribute("title")
   public String setTitle() {
      return "@ModelAttribute 유형";
   }//@ModelAttribute("title")에서 "title"은 모델에 추가되는 속성의 이름입니다
// @ModelAttribute 유형"이 "title"이라는 이름으로 모델에 추가됩니다.
// @ModelAttribute 아무개 아무개가 이름이 명시적으로 지정되는 것이다
   @ModelAttribute
   public void setsubTitle(Model model) {
      model.addAttribute("subtitle", "메소드에 @ModelAttribute 어노테이션 적용하기");
   }// 모델에 "subtitle"이라는 이름의 속성을 추가합니다.
}// 속성의 값은 "메소드에 @ModelAttribute 애노테이션 적용하기"로 설정됩니다.

//@ModelAttribute 애노테이션을 사용할 때 속성의 이름을 명시적으로 지정하지 않았을 경우,
//메소드의 이름이 기본적으로 속성의 이름으로 사용됩니다.
//메소드의 이름은 소문자로 시작하고, 첫 글자를 제외한 부분은 카멜 표기법(Camel Case)을 따릅니다.
//@ModelAttribute
//public String setTitle() {
//   return "title Value";
//}
//속석의 이름인 someTitle 메소드의 이름을 따르게 됩니다
//뷰에서는 ${someTitle} 을 사용하여 해당 속성의 값을 참조할 수 있다