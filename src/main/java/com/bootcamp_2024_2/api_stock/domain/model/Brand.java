package com.bootcamp_2024_2.api_stock.domain.model;

public class Brand {
    private final Long id;
    private final String name;
    private final String description;

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
