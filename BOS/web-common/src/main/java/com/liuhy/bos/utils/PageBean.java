package com.liuhy.bos.utils;

import com.google.gson.annotations.Expose;
import org.hibernate.criterion.DetachedCriteria;

import java.util.ArrayList;
import java.util.List;

public class PageBean {
    private int currentPage;    // 当前页
    private int pageSize;       // 每页记录数

    @Expose  // json序列化此属性
    private int total;          // 总记录数

    private DetachedCriteria detachedCriteria;

    @Expose  // json序列化此属性
    private List rows = new ArrayList(0);   // 查询结果

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
}
