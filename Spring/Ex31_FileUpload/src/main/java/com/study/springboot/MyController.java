package com.study.springboot;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MyController {

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "FileUpload (2)";
    }

    @RequestMapping("/uploadForm")
    public String uploadForm(){
        
        return "fileForm";
    }

    @RequestMapping("/uploadOk")
    public @ResponseBody String uploadOk(HttpServletRequest request)
    {
        int size = 1024 * 1024 * 10; //10M
        String file = "";
        String oriFile = "";
        
        JSONObject obj = new JSONObject();

        try {
            String path = ResourceUtils
                      .getFile("classpath:static/upload/").toPath().toString();
            //System.out.println(path);
            
            MultipartRequest multi = new MultipartRequest(request, path, size,
                                           "UTF-8", new DefaultFileRenamePolicy());
            System.out.println("111111");
            Enumeration files = multi.getFileNames();
            String str = (String)files.nextElement();
            
            file = multi.getFilesystemName(str);
            oriFile = multi.getOriginalFileName(str);
            System.out.println("file:" + file);
            System.out.println("oriFile:" + oriFile);
            
            obj.put("success", new Integer(1));
            obj.put("desc", "업로드 성공");
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("success", new Integer(0));
            obj.put("desc", "업로드 실패");
        }

        return obj.toJSONString();
    }
}
