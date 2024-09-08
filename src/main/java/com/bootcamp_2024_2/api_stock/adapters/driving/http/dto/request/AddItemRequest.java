package com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AddItemRequest(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotBlank(message = "Description cannot be blank")
        @Size(min = 2, max = 50, message = "Description must be between 2 and 50 characters")
        String description,

        @PositiveOrZero(message = "Quantity cannot be negative")
        int quantity,

        @Positive(message = "Price must be positive")
        float price,

        @NotNull(message = "Brand ID cannot be null")
        @Positive(message = "Brand ID must be positive")
        Long idBrand,

        @NotNull(message = "Categories list cannot be null")
        @Size(min = 1, max = 3, message = "Categories list must contain between 1 and 3 items")
        List<AddCategoryByItemRequest> categoriesIdList
) {
}
