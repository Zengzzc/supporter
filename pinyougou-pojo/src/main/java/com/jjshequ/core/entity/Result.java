package com.jjshequ.core.entity;

import java.io.Serializable;

/**
 * 封装用户操作结果数据
 */
public class Result implements Serializable {
    private boolean flag; //用户操作结果
    private String message;//结果信息

    public Result() {
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
