package com.lisk.keyword.util;

import java.util.ArrayList;
import java.util.List;

public class ReturnData<T> {
    //数据集合
    private List<T> rows = new ArrayList<T>();
    //数据总条数
    private int total;

    public ReturnData(List<T> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public ReturnData() {
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
