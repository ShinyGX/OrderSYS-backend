package com.order.sys.bean.dto;

import java.util.HashMap;
import java.util.List;

public class MessageReport {

    private List<MessageEverydayData> messageEverydayData;
    private HashMap<String,Integer> map;

    public MessageReport() {
    }

    public MessageReport(List<MessageEverydayData> messageEverydayData, HashMap<String, Integer> map) {
        this.messageEverydayData = messageEverydayData;
        this.map = map;
    }

    public List<MessageEverydayData> getMessageEverydayData() {
        return messageEverydayData;
    }

    public void setMessageEverydayData(List<MessageEverydayData> messageEverydayData) {
        this.messageEverydayData = messageEverydayData;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
}
