package com.cjwx.titan.crawler.crawler.exceptions;

public class PageBiggerThanMaxSizeException extends Exception {

    private static final long serialVersionUID = -6936502563028478847L;

    long pageSize;

    public PageBiggerThanMaxSizeException(long pageSize) {
        super("Aborted fetching of this URL as it's size ( " + pageSize + " ) exceeds the maximum size");
        this.pageSize = pageSize;
    }

    public long getPageSize() {
        return pageSize;
    }
}
