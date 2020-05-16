package com.order.sys.controller;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.services.FileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServices fileServices;

    @PostMapping("/upload")
    BaseMessage<String> upload(
            @RequestParam("id") Integer userId,
            @RequestParam("img") MultipartFile file)
    {
        return fileServices.setImage(userId,file);
    }
}
