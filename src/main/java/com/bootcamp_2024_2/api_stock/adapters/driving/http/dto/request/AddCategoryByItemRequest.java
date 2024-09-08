package com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddCategoryByItemRequest(
        @NotBlank(message = "Name cannot be blank")
        long id
) {
}
