package com.springmvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

@Controller
public class BookController {
    @Autowired
    private BookService bookService; 
    @RequestMapping(value="/books", method=RequestMethod.GET) 


     public String requestBookList(Model model) {  
         List<Book> list = bookService.getAllBookList();
        model.addAttribute("bookList", list); 
        return "books"; 
    } 
}

// ➊ 	웹 애플리케이션을 개발할 때 유연성과 확장성을 높이려면 BookController 컨트롤러에서 저장소 객체인 BookRepository로 직접 접근하지 말고,
// 		서비스 객체인 BookService로 저장소 객체에 접근해야 합니다.

// ➋ 	요청 매핑 @RequestMapping: 컨트롤러에서 웹 요청을 처리할 메서드에 매핑하는 애너테이션 설정입니다.
//		웹 브라우저의 주소창에 ‘http://localhost:8080/BookMarket/books’를 입력하면
//		@RequestMapping(“/books”)로 매핑되어 요청 처리 메서드 requestBookList()가 실행됩니다.

// ➌ 	요청 처리 메서드: requestBookList()는 웹 요청을 처리할 메서드입니다.

// ➍ 	모델 데이터: 모델 속성 이름 bookList에 저장된 도서 목록을 저장합니다.

// ➎ 	뷰 이름: books로 반환하므로 JSP 파일은 books.jsp가 됩니다.
