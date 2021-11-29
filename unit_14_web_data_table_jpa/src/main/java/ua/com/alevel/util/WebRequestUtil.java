package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.datatable.DataTableRequest;

import java.util.Objects;

public final class WebRequestUtil {

    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";
    public static final String DEFAULT_SORT_PARAM_VALUE = "id";
    public static final String DEFAULT_ORDER_PARAM_VALUE = "desc";
    public static final int DEFAULT_PAGE_PARAM_VALUE = 1;
    public static final int DEFAULT_SIZE_PARAM_VALUE = 10;

    private WebRequestUtil() { }

    public static DataTableRequest generateDataTableRequest(WebRequest request) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        int page = StringUtils.isNotBlank(request.getParameter(PAGE_PARAM)) ? Integer.parseInt(Objects.requireNonNull(request.getParameter(PAGE_PARAM))) : DEFAULT_PAGE_PARAM_VALUE;
        int size = StringUtils.isNotBlank(request.getParameter(SIZE_PARAM)) ? Integer.parseInt(Objects.requireNonNull(request.getParameter(SIZE_PARAM))) : DEFAULT_SIZE_PARAM_VALUE;
        String sort = StringUtils.isNotBlank(request.getParameter(SORT_PARAM)) ? Objects.requireNonNull(request.getParameter(SORT_PARAM)) : DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(request.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(request.getParameter(ORDER_PARAM)) : DEFAULT_ORDER_PARAM_VALUE;
        dataTableRequest.setPage(page);
        dataTableRequest.setSize(size);
        dataTableRequest.setSort(sort);
        dataTableRequest.setOrder(order);
        return dataTableRequest;
    }
}
