package com.jjshequ.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 1、分页结果封装对象：总条数；结果集
 * 2、实现序列化接口：可转化为二进制数据，
 */
public class PageResult implements Serializable {

    private Long total; //总条数
    private List rows;  //结果集

    public PageResult() {
    }

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }


}
