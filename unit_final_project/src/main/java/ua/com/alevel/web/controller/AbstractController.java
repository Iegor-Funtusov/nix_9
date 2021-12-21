package ua.com.alevel.web.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.ResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebUtil.DEFAULT_ORDER_PARAM_VALUE;

public abstract class AbstractController {

    protected void showInfo(Model model, String message) {
        model.addAttribute("message", message);
        showMessage(model, true);
    }

    protected void showInfo(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("message", message);
    }

    protected void showError(Model model, String message) {
        model.addAttribute("errorMessage", message);
        showMessage(model, true);
    }

    protected void showError(RedirectAttributes redirectAttributes, String error) {
        redirectAttributes.addFlashAttribute("errorMessage", error);
    }

    protected void showWarn(Model model, String message) {
        model.addAttribute("warnMessage", message);
        showMessage(model, true);
    }

    protected void showWarn(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("warnMessage", message);
    }

    protected void showMessage(Model model, boolean show) {
        model.addAttribute("showMessage", show);
    }

    protected static class HeaderName {

        private String columnName;
        private String tableName;
        private String dbName;

        public HeaderName(String columnName, String tableName, String dbName) {
            this.columnName = columnName;
            this.tableName = tableName;
            this.dbName = dbName;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }
    }

    protected static class HeaderData {

        private String headerName;
        private boolean active;
        private boolean sortable;
        private String sort;
        private String order;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public boolean isSortable() {
            return sortable;
        }

        public void setSortable(boolean sortable) {
            this.sortable = sortable;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        @Override
        public String toString() {
            return "HeaderData{" +
                    "headerName='" + headerName + '\'' +
                    ", active=" + active +
                    ", sortable=" + sortable +
                    ", sort='" + sort + '\'' +
                    ", order='" + order + '\'' +
                    '}';
        }
    }

    protected void initDataTable(
            PageData<? extends ResponseDto> response,
            HeaderName[] columnNames,
            Model model) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getDbName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("pageData", response);
    }

    public ModelAndView findAllRedirect(WebRequest request, ModelMap model, String redirectUrl) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/" + redirectUrl, model);
    }
}
