package ua.com.alevel.view.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        showMessage(redirectAttributes, true);
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

    protected void showMessage(RedirectAttributes redirectAttributes, boolean show) {
        redirectAttributes.addFlashAttribute("showMessage", show);
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
}
