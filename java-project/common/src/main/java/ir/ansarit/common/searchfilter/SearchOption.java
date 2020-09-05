package ir.ansarit.common.searchfilter;

import java.util.List;

public class SearchOption {

    private List<FilterParam> filterParams;

    private int pageSize;

    private int pageNumber;


    public List<FilterParam> getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(List<FilterParam> filterParams) {
        this.filterParams = filterParams;
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
}
