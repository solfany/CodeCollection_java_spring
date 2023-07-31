package com.springmvc.domain;
import org.springframework.web.multipart.MultipartFile;

// 계층적 구조
// 관심사를 분리해서 각 계층을 느슨하게 결합하고 계층 간에 유연하게 동작시킬 수 있음
// 웹 애플리케이션을 좀 더 효율적으로 개발하고,
// 개발한 이후 유지 보수를 쉽게 하기 위해 시스템 구조를 계층화하여 개발하는 것이 일반적임.
// '도메인 객체(Domain Object)
// 1- 객체 정보를 저장하는 데이터 모델
// '퍼시스턴스 계층(Persistence Layer) = 데이터 액세스 계층 - 데이터베이스나 파일에 접근하여 데이터를 처리하는 곳
// 서비스 계층(Service Layer) = 비즈니스 계층
// - 애플리케이션이 제공하는 포괄적인 서비스들을 표현
// - 클라이언트에서 요청한 데이터를 가져오거나 변경하기 위해서 퍼시스턴스 계층을 호출하며,
// - 프레젠테이션 계층과 퍼시스턴스 계층 사이의 연결하는 역할
// 프레젠테이션 계층(Presentation Layer)
// - 애플리케이션과 사용자와의 최종 접점
// - 사용자로부터 데이터를 입력받거나 웹 서버로부터 데이터를 출력해 사용자에게 보이는 계층
// - 외부로부터 애플리케이션의 요청을 받아들여 처리하며 등시에 처리된 결과를 사용자에게 보여즘
// - 컨트롤러 --> 뷰

public class Book {
	private String bookId;				// 도서 ID
	private String name;				// 도서명
	private int unitPrice;				// 가격
	private String author;				// 저자
	private String description;			// 설명
	private String publisher;			// 출판사
	private String category;			// 분류
	private long unitsInStock;			// 재고수
	private String releaseDate;			// 출판일(월/년)
	private String condition;			// 신규 도서 OR 중고 도서 OR 전자책
	private MultipartFile bookImage;	// 도서 이미지
	
	public Book() {
		super();
	}

	public Book(String bookId, String name, int unitPrice) {
        super();
        this.bookId = bookId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(long unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

	public MultipartFile getBookImage() {
		return bookImage;
	}

	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}    
    
}
