

package utils;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

// 도우미 클래스를 별도로 만든다.
public class JSFunction {
    // 메시지 알림창을 띄운 후 명시한 URL로 이동한다.
    public static void alertLocation(String msg, String url, JspWriter out) {
        try {
            String script =  "" 
            							+"<script>"
            							+ "alert('" + msg + "');"
            							+ " location.href='" + url + "';" 
            							+" </script>";
            out.println(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alertBack(String msg, JspWriter out) {
        try {
            String script = "" 
            							+ "<script>"  
            							+ "alert('" + msg + "');"
            							+"history.back();"
            							+	"</script>";
            out.println(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alertLocation(HttpServletResponse resp, String msg, String url) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = ""
            							+ "<script>"
            							+ "alert('" + msg + "');"
            							+ "location.href='" + url + "';"
            							+ "</script>";
            writer.print(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alertBack(HttpServletResponse resp, String msg) {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            String script = ""
            							+ "<script>"
            							+" alert('" + msg + "');"
            							+ "history.back();"
            							+ "</script>";
            writer.print(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// JSFunction 클래스는 실행 가능한 클래스가 아니다.
// import  utils.JSFunction;
//JSFunction 클래스를  import 한다. (<% paga import = "utils.JSFunction" %>)
// JSFunction 클래스의 메소드를 호출하여 JavaScript 코드를 생성합니다. 