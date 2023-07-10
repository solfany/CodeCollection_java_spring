package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdditionalServelt01 ㄴ어류ㅓㄴ아류ㅏㄴ
 */
@WebServlet("/AdditionalServlet01")
public class AdditionalServlet01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
//       자동으로 추가된 이 라인은 클레스를 구분하기 위한 값으로 사람에게는 주민번호 역할을 한다고 비유됨
//       객체의 직렬화와 같이 객체에 저장된 데이터를 일렬 입출력 할떄
//       JVM은 같은 클래스 이름과 버전 ID를 가진 객체를 출력한다
//       JVM은 버전 ID 가 다른 객체의 직렬화된 형태와 연결하는 것을 거부한다
//       클래스는 명시적으로 serialVersionUID 필드를 정의해 클래스 버전에 따른 고유번호를 포함하기 때문입니다
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdditionalServlet01() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    //request 는 전송받는 객체, response는 다른 창으로 데이터를 전송해주는 객체\
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//요청처리, 응답처리, 예외처리 매개변수로 넣어준것임
		int num1 = 20;
		int num2 = 10;
		int add = num1 + num2;
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Addition</title></head>");
		out.println("<body>");
		out.println(num1 + "+" + num2 + "=" + add);
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
