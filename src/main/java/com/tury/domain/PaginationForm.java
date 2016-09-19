package com.tury.domain;

public class PaginationForm {

    private int itemPerPage = 10;
    private int totalSize;
    private int currentPage = 1;

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Set current page.
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if(currentPage < 1) {
            // If current page < 1, default value will be 1.
            this.currentPage = 1;
        }
    }

    /**
     * Get start row of page. Example : If total size = 31, item per page = 10, current page = 2 then start row will be 10
     */
    public int getRowStart() {
        int rowStart = 0;
        if(currentPage > getLastPage()) {
            rowStart = (getLastPage() - 1) * itemPerPage;
        } else if(currentPage > 0) {
            rowStart = (currentPage - 1) * itemPerPage;
        }
        return rowStart;
    }

    /**
     * Get last page (max page).
     */
    public int getLastPage() {
        int lastPage = 1;
        if(totalSize > 0 && itemPerPage > 0) {
            lastPage = (int)Math.ceil((double)totalSize / (double)itemPerPage);
        }
        return lastPage;
    }

    /**
     * Get previous page.
     */
    public int getPreviousPage() {
        // Return 1 if current page < 1,
        int previousPage = -1;
        if(currentPage >= getLastPage()) {
            // Return last page - 1 if current page >= last page.
            return getLastPage() - 1;
        } else if(currentPage > 1) {
            previousPage = currentPage - 1;
        }
        return previousPage;
    }

    /**
     * Get next page.
     */
    public int getNextPage() {
        // Return last page if current page >= last page.
        int nextPage = getLastPage();
        if(currentPage < getLastPage()) {
            nextPage = currentPage + 1;
        }
        return nextPage;
    }

    /**
     * Validate page number. If validate fail, change current page to redirect.
     */
    public void handlePageToRedirect(String page) {
        if(page != null) {
//            // If page is not numeric value, current page will be 1.
//            if(!Numeric.isIntegerConvertible(page)) {
//                currentPage = 1;
//                return;
//            }
            currentPage = Integer.parseInt(page);
            if(currentPage < 1) {
                // If current page < 1, current page will be 1.
                currentPage = 1;
            } else if (currentPage > getLastPage()) {
                // If current page > last page, current page will be last page.
                currentPage = getLastPage();
            }
        }
    }

    public String toString() {
        return "itemPerPage : " + itemPerPage + ", totalSize : " + totalSize + ", currentPage : " + currentPage +
                ", rowStart : " + getRowStart() + ", lastPage : " + getLastPage() + ", previousPage : " + getPreviousPage() +
                ", nextPage : " + getNextPage();
    }
}
