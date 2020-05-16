package com.order.sys.services;

import com.order.sys.bean.dto.BaseMessage;
import org.springframework.web.multipart.MultipartFile;

public interface FileServices {

    BaseMessage<String> setImage(int userId,MultipartFile img);
}
