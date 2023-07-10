package com.springmvc.repository;

import java.util.List;
import com.springmvc.domain.Book;

public interface BookRepository {
    List<Book> getAllBookList();
}
// 도서 정보를 관리하는 퍼시스턴스 계층
// 데이터 액세스 계층
// 특정 클래스에 @Repository를 선언하면 해당 클래스는 저장소 객체라는 의미
