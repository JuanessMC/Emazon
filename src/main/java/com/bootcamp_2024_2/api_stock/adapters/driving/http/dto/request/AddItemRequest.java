package com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request;


import java.util.List;

public record AddItemRequest(String name,
        String description,
        int quantity,
        float price,
        Long idBrand,
        List<AddCategoryByItemRequest>categoriesIdList) {

}
