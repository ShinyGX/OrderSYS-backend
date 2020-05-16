package com.order.sys.services.impl;

import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.model.BookUser;
import com.order.sys.constants.ErrorCode;
import com.order.sys.repository.BookUserRepository;
import com.order.sys.services.FileServices;
import com.order.sys.util.FindObjUtil;
import com.order.sys.util.MessageInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class FileServicesImpl implements FileServices {

    @Autowired
    private BookUserRepository bookUserRepository;


    @Value(value = "${ResourcePath}")
    private String recoursePath;

    @Value(value = "${getResourcePath}")
    private String urlPath;


    @Override
    public BaseMessage<String> setImage(int userId,MultipartFile img) {
        BookUser bookUser = FindObjUtil.findById(userId,bookUserRepository);
        if(bookUser == null)
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.OBJECT_NOT_FOUND);


        String targetPath = recoursePath + userId + "\\";
        String url = urlPath + userId +"/";
        try {
            byte[] imgBytes = img.getBytes();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = dateFormat.format(System.currentTimeMillis()) + "_" +img.getOriginalFilename();
            File targetFile = new File(targetPath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            Path path = Paths.get(targetPath,fileName);
            Files.write(path,imgBytes);

            url += fileName;
            bookUser.setUser_icon(url);

            bookUserRepository.save(bookUser);
            return MessageInputUtil.baseMessageSuccessInput("Success");

        } catch (IOException e) {
            e.printStackTrace();
            return MessageInputUtil.baseMessageErrorInput(ErrorCode.UNKNOWN_ERROR);
        }

    }
}
