package ua.com.alevel.persistence.datatable;

import java.util.Collections;
import java.util.Map;

public class DataTableRequest {

    private int page;
    private int size;
    private String order;
    private String sort;
    private Map<String, String[]> requestParamMap;

    public DataTableRequest() {
        requestParamMap = Collections.emptyMap();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, String[]> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(Map<String, String[]> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }
}
