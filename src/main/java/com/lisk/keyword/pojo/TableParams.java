package com.lisk.keyword.pojo;

public class TableParams<E> {
    private String sortName;
    private String sortOrder;
    private int pageSize;
    private int pageNumber;
    private E data;

    public TableParams(String sortName, String sortOrder, int pageSize, int pageNumber, E data) {
        this.sortName = sortName;
        this.sortOrder = sortOrder;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.data = data;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public TableParams() {

    }
}
