package com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddBrandRequest(
        @NotBlank(message = "Field 'name' cannot be null")
        @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
        String name,
        @NotBlank(message = "Field 'description' cannot be null")
        @Size(min = 2, max = 120, message = "Description must be between 2 and 120 characters")
        String description){
}
