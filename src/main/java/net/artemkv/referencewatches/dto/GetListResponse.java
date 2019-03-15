package net.artemkv.referencewatches.dto;

import java.util.List;

public class GetListResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long total;
    private int count;
    private List<T> results;

    public GetListResponse(
        int pageNumber, int pageSize, long total, int count, List<T> results) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public List<T> getResults() {
        return results;
    }
}
