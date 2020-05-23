package com.order.sys.services.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.bean.dto.MessageSms;
import com.order.sys.constants.ALiYunAPI;
import com.order.sys.constants.ErrorCode;
import com.order.sys.services.SmsServices;
import com.order.sys.util.MessageInputUtil;
import org.springframework.stereotype.Service;

@Service
public class SmsServicesImpl implements SmsServices {

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";


    @Override
    public BaseMessage<MessageSms> getCode(String phone) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",ALiYunAPI.accessKey,ALiYunAPI.accessKeySecret);

        try {
            IAcsClient acsClient = new DefaultAcsClient(profile);

            int code = (int)((Math.random()* 9+1)*100000);


            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phone);
            request.putQueryParameter("SignName", "OrderSYS");
            request.putQueryParameter("TemplateCode", "SMS_190270038");
            request.putQueryParameter("TemplateParam","{\"code\":" + String.valueOf(code) + "}");

            CommonResponse response = acsClient.getCommonResponse(request);

            MessageSms sms = new MessageSms(code);
            return MessageInputUtil.baseMessageSuccessInput(sms);
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return MessageInputUtil.baseMessageErrorInput(ErrorCode.A_LI_YUN_API_ERROR);
    }

    @Override
    public BaseMessage<String> returnOrderMessage(
            String phone,String name, String officeName, String officeAddress, String code, String time) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",ALiYunAPI.accessKey,ALiYunAPI.accessKeySecret);

        try {
            IAcsClient acsClient = new DefaultAcsClient(profile);


            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phone);
            request.putQueryParameter("SignName", "OrderSYS");
            request.putQueryParameter("TemplateCode", "SMS_190727837");


            String param = "{\"name\":" + name + ",\"office\":" + officeName + ",\"address\":" + officeAddress +
                    ",\"time\":" + time + ",\"code\": "+code +"}";
            request.putQueryParameter("TemplateParam",param);

            CommonResponse response = acsClient.getCommonResponse(request);
            return MessageInputUtil.baseMessageSuccessInput("");
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return MessageInputUtil.baseMessageErrorInput(ErrorCode.A_LI_YUN_API_ERROR);
    }
}
