package com.bootcamp_2024_2.api_stock.domain.util;

import java.util.List;

public class Paginate<T> {
    private final Integer totalPages;
    private final Integer currentPage;
    private final Integer totalItems;
    private final Integer pageSize;
    private final List<T> content;

    public Paginate(Integer totalPages, Integer currentPage, Integer totalItems, Integer pageSize, List<T> content) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.pageSize = pageSize;
        this.content = content;
    }

    public static <T> Paginate<T> of(Integer totalPages, Integer currentPage, Integer totalItems, Integer pageSize, List<T> items) {
        return new Paginate<>(totalPages, currentPage, totalItems, pageSize, items);
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public List<T> getContent() {
        return content;
    }
}
