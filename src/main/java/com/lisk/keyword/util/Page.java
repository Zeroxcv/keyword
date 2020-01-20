package com.lisk.keyword.util;


public class Page {
    //开始页数
    private int start;
    //每页显示个数
    private int count;
    //总个数
    private int total;
    private static final int defaultCount = 10;

    public Page() {
    }

    public Page(int start, int count, int total) {
        this.start = start;
        this.count = count;
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static int getDefaultCount() {
        return defaultCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
