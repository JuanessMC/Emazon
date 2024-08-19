package com.bootcamp_2024_2.api_stock.adapters.driving.http.controller;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.bootcamp_2024_2.api_stock.domain.api.ICategoryServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapping;
    private final ICategoryRequestMapper categoryRequestMapping;

    @PostMapping("/")
    public ResponseEntity<String> addCategory(@RequestBody @Valid AddCategoryRequest categoryRequest){
        categoryServicePort.saveCategory(categoryRequestMapping.addRequestToCategory(categoryRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body("The category has been successfully recorded");

    }
}
