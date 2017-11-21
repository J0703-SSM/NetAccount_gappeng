package com.lanou.util;

import java.util.List;

/**
 * Created by dllo on 17/11/14.
 */
public class PageBean<T> {
    private int pageNum; //第几页
    private int pageSize; //每页显示的条数
    private int totalCount; //总记录数
    private int startIndex; //开始索引
    private int totalPage; //总页数
    private String condition; //条件
    private String column;
    private List<T> data;
    private int id;
    private T t;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", startIndex=" + startIndex +
                ", totalPage=" + totalPage +
                ", condition='" + condition + '\'' +
                ", column='" + column + '\'' +
                ", data=" + data +
                ", id=" + id +
                '}';
    }

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, int totalCount, String condition, int id) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.condition = condition;
        this.id = id;
    }

    public PageBean(int pageNum, int pageSize, int totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.startIndex = (pageNum - 1) * pageSize;
        if (totalCount % pageSize > 0) {
            this.totalPage = totalCount / pageSize + 1;
        } else {
            this.totalPage = totalCount / pageSize;
        }
    }

    public PageBean(int pageNum, int pageSize, int totalCount, String condition, String column) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.startIndex = (pageNum - 1) * pageSize;
        if (totalCount % pageSize > 0) {
            this.totalPage = totalCount / pageSize + 1;
        } else {
            this.totalPage = totalCount / pageSize;
        }
        this.condition = condition;
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}


