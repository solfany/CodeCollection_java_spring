package ch15;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class WebImageDownload {

    public static void main(String[] args) {
        String website = "http://www.gstatic.com/webp/gallery/1.sm.jpg";
        System.out.println(website + " 사이트에서 이미지를 다운로드합니다.");
        URL url;
        byte[] buffer = new byte[2048];

        try {
            url = new URL(website);
            try (InputStream in = url.openStream();
                 FileOutputStream out = new FileOutputStream("test.jpg")) {
                int length;
                while ((length = in.read(buffer)) != -1) {
                    System.out.println(length + "바이트 크기만큼 읽었습니다!");
                    out.write(buffer, 0, length);
                }
                System.out.println("이미지 다운로드가 완료되었습니다.");
            } catch (Exception e) {
                System.out.println("입출력 예외: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("URL 예외: " + e.getMessage());
        }
    }
}

// 설정한 주소에서 이미지 파일을 다운로드 하는 로직 
