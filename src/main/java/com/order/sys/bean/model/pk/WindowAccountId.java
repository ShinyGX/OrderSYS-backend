package com.order.sys.bean.model.pk;

import java.io.Serializable;

public class WindowAccountId implements Serializable {
    private Integer window_id;
    private Integer account_id;

    public WindowAccountId() {
    }

    public WindowAccountId(Integer window_id, Integer account_id) {
        this.window_id = window_id;
        this.account_id = account_id;
    }

    public Integer getWindow_id() {
        return window_id;
    }

    public void setWindow_id(Integer window_id) {
        this.window_id = window_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }
}
