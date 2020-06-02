package com.order.sys.bean.dto;

public class PageMessage<T> {
    private String msg;
    private int code;
    private T data;

    private int curPage;
    private int maxPage;

    public PageMessage() {
    }

    public PageMessage(String msg, int code, T data, int curPage, int maxPage) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.curPage = curPage;
        this.maxPage = maxPage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
