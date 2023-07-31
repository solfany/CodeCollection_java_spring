package com.springmvc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.Book;
import com.springmvc.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
 
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }
    public List<Book> getBookListByCategory(String category) { 
        List<Book> booksByCategory = bookRepository.getBookListByCategory(category); 
        return booksByCategory; 
    } 
    
    // 필터를 적용하여 도서 목록을 가져오는 메서드입니다.
    public Set<Book> getBookListByFilter(Map<String, List<String>> filter) {
        Set<Book> booksByFilter = bookRepository.getBookListByFilter(filter);
        return booksByFilter;
    }

    // 특정 도서 ID에 해당하는 도서를 가져오는 메서드입니다.
    public Book getBookById(String bookId) {
        Book bookById = bookRepository.getBookById(bookId);
        return bookById;
        // bookId를 기준으로 BookRepository를 통해 해당 도서를 가져옵니다.
    }
    public void setNewBook(Book book) {
    	bookRepository.setNewBook(book);
    	// 신규 도서 정보를 저장소 객체에 저장하는 메서드입니다.
    }
}
