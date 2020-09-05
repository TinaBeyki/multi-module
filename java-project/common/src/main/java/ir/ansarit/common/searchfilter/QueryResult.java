package ir.ansarit.common.searchfilter;

import java.util.List;

public class QueryResult<T> {

    private List<T> entityList;
    private int pageSize;
    private int pageNumber;
    private long totalRecord;

    public QueryResult(List<T> entityList) {
        this.entityList = entityList;
    }

    public QueryResult(int pageNumber) {
        super();
        this.pageNumber = pageNumber;
        this.pageSize = 0;
        this.totalRecord = 0;
        this.entityList = null;
    }

    public QueryResult(String pageSize) {
        super();
        this.pageSize = Integer.parseInt(pageSize);
        this.pageNumber = 0;
        this.totalRecord = 0;
        this.entityList = null;
    }
    public QueryResult(List<T> entityList, int pageSize, int pageNumber, long totalRecord) {
        this.entityList = entityList;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalRecord = totalRecord;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
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

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }
}
