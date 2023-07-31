package com.springmvc.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.Book;
import com.springmvc.service.BookService;

// 컨트롤러
// 웹 브라우저에서 들어온 사용자 요청을 구현된 메서드에서 처리하고
// 그 결과를 뷰에 전달하는 스프링의 빈 객체
@Controller
@RequestMapping("/books")
public class BookController {
	
    @Autowired
    private BookService bookService; 
// @GetMapping 어노테이션은 HTTP GET 요청이 "/books" URL로 들어올 때
// requestBookList() 메소드를 실행하도록 설정

    @GetMapping
    public String requestBookList(Model model) {  
        List<Book> list = bookService.getAllBookList();
// 도서 목록을 가져옵니다.
        model.addAttribute("bookList", list); 
// 도서 목록을 모델 속성 bookList에 저장합니다.
        return "books"; 
    } 
    
    @GetMapping("/all")
    public ModelAndView requestAllBooks() {
    	ModelAndView modelAndView = new ModelAndView();
    	List<Book> list = bookService.getAllBookList();
    	modelAndView.addObject("bookList", list);
    	modelAndView.setViewName("books");
    	return modelAndView;
    }
    
    @GetMapping("/{category}") 
    public String requestBooksByCategory(@PathVariable("category") String bookCategory, Model model) { 
        List<Book> booksByCategory = bookService.getBookListByCategory(bookCategory); 
        model.addAttribute("bookList", booksByCategory); 
        return "books"; 
    }
    
    @GetMapping("/filter/{bookFilter}")
    public String requestBookByFilter(
    		// @MatrixVariable 어노테이션은 URL 경로의 일부인 행렬 변수를 메소드 인자
    		@MatrixVariable
    		// PathVar 속성은 URL 경로에서 행렬 변수의 이름
    		(pathVar="bookFilter") Map<String, List<String>> bookFilter,
    		Model model ) {
    	Set<Book> booksByFilter =
    			bookService.getBookListByFilter(bookFilter);
    	// bookService 객체의 getBookListByFilter 메소드를 호출하여
    	// bookFilter를 인자로 전달하고, 결과를 booksByFilter에 저장
    	model.addAttribute("bookList", booksByFilter);
    	return "books";
    }
    
    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model) {
    	// @RequestParam 어노테이션은 요청의 쿼리 파라미터를 메소드 인자로 바인딩하도록 지시
    	// "id"라는 이름의 쿼리 파라미터를 가져와서 String 타입의 bookId 인자에 바인딩합니다.
    	Book bookById = bookService.getBookById(bookId);
    	model.addAttribute("book", bookById);
    	return "book";
    }
    // 이 값은 뷰 리졸버에 의해 처리되어 실제 뷰를 결정하는 데 사용됩니다.
    // WEB-INF/views/ 디렉토리 내의 "books.jsp" 파일이 뷰로 사용됩니다.
    
    @GetMapping("/add")
    public String requestAddBookForm(@ModelAttribute("NewBook") Book book) {
    	return "addBook";
    // book 파라미터를 받아옵니다.
    // book 객체를 "NewBook"이라는 이름으로 모델에 추가한다.
    // addBook 뷰를 반환하여 클라이언트에게 응답합니
    }
    @PostMapping("/add")
    public String submitAddNewBook(@ModelAttribute("NewBook") Book book) {
    	bookService.setNewBook(book);	// book객체를 bookService라는 서비스 setNewBook 메소드를 호출하여 저장하는 역할
    	return "redirect:/books";
    }	// redirect:/books를 반환하여 "/books" 경로로 리다이렉트 합니다.
    @ModelAttribute
    public void addAttributes(Model model) {
    	model.addAttribute("addTitle", "신규 도서 등록");
    }	// 모델에 "addTitle"이라는 이름으로 "신규 도서 등록" 값을 추가합니다.
    
    @InitBinder
    // 사용자가 입력한 데이터가 커맨드 객체의 프로퍼티에 매핑되기 전에 데이터 바인딩을 
    // 사용자 정의(Customizing) 가능
    public void initBinder(WebDataBinder binder) {
    	binder.setAllowedFields("bookId", "name", "unitPrice", "author", "description", "publisher", "category", "unitsInStock", "totalPages", "releaseDate", "condition");
    }	// 바인딩할 필드를 제한합니다. GET 요청으로는 도서 등록 폼을 제공하고, POST 요청으로는 도서를 저장하고 "/books"로 리다이렉트합니다.
    
    
}
// ModelAndView
// 객체는 모델 데이터와 뷰 이름을 함께 가지고 있으므로, 이 메서드를 호출한 측은
// 이 객체를 통해 데이터와 뷰를 동시에 받을 수 있습니다.
// requestBookList() 메소드와 requestAllBooks()메소드가
// 모두 bookService.getAllBookList()를 호출하고,
// 이 결과를 "BookList"라는 이름으로 모델에 추가한 후, "books"라는 뷰를 반환하기 때문에 두 메소드는 똑같은 겨로가를 보여주게 됩니다.


// ➊ 	웹 애플리케이션을 개발할 때 유연성과 확장성을 높이려면 BookController 컨트롤러에서 저장소 객체인 BookRepository로 직접 접근하지 말고,
// 		서비스 객체인 BookService로 저장소 객체에 접근해야 합니다.

// ➋ 	요청 매핑 @RequestMapping: 컨트롤러에서 웹 요청을 처리할 메서드에 매핑하는 애너테이션 설정입니다.
//		웹 브라우저의 주소창에 ‘http://localhost:8080/BookMarket/books’를 입력하면
//		@RequestMapping(“/books”)로 매핑되어 요청 처리 메서드 requestBookList()가 실행됩니다.

// ➌ 	요청 처리 메서드: requestBookList()는 웹 요청을 처리할 메서드입니다.

// ➍ 	모델 데이터: 모델 속성 이름 bookList에 저장된 도서 목록을 저장합니다.

// ➎ 	뷰 이름: books로 반환하므로 JSP 파일은 books.jsp가 됩니다.


// 데이터 바인딩이란 웹 페이지에서 전달되는 요청 파라미터 값을 동적으로 도메인 객체의 프로퍼티에 설정해 주는 것을 의미합니다. 
// 일반적으로 웹 애플리케이션에서 사용자가 입력하여 웹 페이지에서 전달되는 값은 문자열입니다. 
// 이 문자열로 전달된 파라미터는 도메인 객체의 프로퍼티 타입(int, boolean, char 등)에 맞게 변환해야 합니다. 
// 이렇게 사용자가 입력한 문자열 값을 프로퍼티 타입에 맞추어 자동으로 변환하여 할당하는 것을 데이터 바인딩이라고 합니다.