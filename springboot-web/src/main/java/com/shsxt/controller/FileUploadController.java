package com.shsxt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 */
@RestController
public class FileUploadController {
    @RequestMapping("/fileUpload")
    public Map<String,String> fileUpload(MultipartFile fileName) throws IOException {
       System.out.println("文件名:"+fileName.getOriginalFilename());
       //转存至本地
        fileName.transferTo(new File("E://"+fileName.getOriginalFilename()));
        Map<String,String> map =new HashMap<>();
        map.put("code","200");
        map.put("message","success");
        return  map;

    }
}
