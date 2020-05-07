package com.order.sys.bean.dto;

import com.order.sys.bean.model.ComWindows;

public class MessageValidateWindow {
    private ComWindows windowMessage;
    private MessageMission messageMission;
    private Boolean isOnePerson;

    public MessageValidateWindow() {
    }

    public MessageValidateWindow(ComWindows comWindows, Boolean isOnePerson) {
        this.windowMessage = comWindows;
        this.isOnePerson = isOnePerson;
    }

    public MessageValidateWindow(ComWindows windowMessage, MessageMission messageMission, Boolean isOnePerson) {
        this.windowMessage = windowMessage;
        this.messageMission = messageMission;
        this.isOnePerson = isOnePerson;
    }

    public MessageMission getMessageMission() {
        return messageMission;
    }

    public void setMessageMission(MessageMission messageMission) {
        this.messageMission = messageMission;
    }

    public ComWindows getWindowMessage() {
        return windowMessage;
    }

    public void setWindowMessage(ComWindows windowMessage) {
        this.windowMessage = windowMessage;
    }

    public Boolean getOnePerson() {
        return isOnePerson;
    }

    public void setOnePerson(Boolean onePerson) {
        isOnePerson = onePerson;
    }
}
