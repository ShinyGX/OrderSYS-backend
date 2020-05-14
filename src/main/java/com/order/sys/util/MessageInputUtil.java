package com.order.sys.util;



import com.order.sys.bean.dto.BaseMessage;
import com.order.sys.constants.ErrorCode;
import com.order.sys.services.StaffRecodeServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageInputUtil {


    private static Map<Integer, String> errorCodeMap = new HashMap<Integer, String>(){};
    {
        errorCodeMap.put(ErrorCode.OBJECT_NOT_FOUND, "Object Not Found");
        errorCodeMap.put(ErrorCode.PERMISSION_DENY, "Permission Deny");
        errorCodeMap.put(ErrorCode.UNKNOWN_ERROR, "Unknown error");
        errorCodeMap.put(ErrorCode.ACTION_ALREADY_DONE, "Action Already Done");
        errorCodeMap.put(ErrorCode.OBJECT_ALREADY_EXIST, "Object Already Exist");
        errorCodeMap.put(ErrorCode.SUCCESS, "Success");
        errorCodeMap.put(ErrorCode.A_LI_YUN_API_ERROR,"ALiYun Api Error");
    }


    public static <T> BaseMessage<T> baseMessageErrorInput(int errorCode) {
        return baseMessageErrorInput(errorCodeMap.get(errorCode), errorCode);
    }

    public static <T> BaseMessage<T> baseMessageSimpleInputRecode(
            String alter, T msg, StaffRecodeServices recodeServices, Integer actionId, Integer accountId, String desc) {
        if (msg != null) {
            recodeServices.recodeStaffAction(actionId, accountId, desc);
            return baseMessageSuccessInput(msg);
        }
        return baseMessageErrorInput(alter, ErrorCode.OBJECT_NOT_FOUND);
    }

    public static <T> BaseMessage<List<T>> baseMessageListInput(String alter, List<T> msg) {
        return baseMessageInput(!msg.isEmpty(), alter, msg, ErrorCode.OBJECT_NOT_FOUND);
    }

    public static <T> BaseMessage<T> baseMessageSimpleInput(String alter, T msg) {
        return baseMessageInput(msg != null, alter, msg, ErrorCode.OBJECT_NOT_FOUND);
    }

    public static <T> BaseMessage<T> baseMessageErrorInput(String alter, int errorCode) {
        return baseMessageInput(false, alter, null, errorCode);
    }

    public static <T> BaseMessage<T> baseMessageSuccessInput(T msg) {
        return baseMessageInput(true, "", msg, ErrorCode.UNKNOWN_ERROR);
    }

    public static <T> BaseMessage<T> baseMessageInput(boolean success, String alter, T msg, int errorCode) {
        if (success) {
            BaseMessage<T> bm = new BaseMessage<>();
            bm.setCode(ErrorCode.SUCCESS);
            bm.setData(msg);
            return bm;
        } else {
            BaseMessage<T> bm = new BaseMessage<>();
            bm.setCode(errorCode);
            bm.setMsg(alter);
            return bm;
        }
    }


}
