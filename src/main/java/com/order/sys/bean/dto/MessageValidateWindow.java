package com.order.sys.bean.dto;

import com.order.sys.bean.model.ComWindows;

public class MessageValidateWindow {
    private ComWindows windowMessage;
    private Boolean isOnePerson;

    public MessageValidateWindow() {
    }

    public MessageValidateWindow(ComWindows comWindows, Boolean isOnePerson) {
        this.windowMessage = comWindows;
        this.isOnePerson = isOnePerson;
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
