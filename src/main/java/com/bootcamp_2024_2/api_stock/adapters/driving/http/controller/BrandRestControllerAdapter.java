package com.bootcamp_2024_2.api_stock.adapters.driving.http.controller;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.BrandResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.IBrandRequestMapper;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.IBrandResponseMapper;
import com.bootcamp_2024_2.api_stock.domain.api.IBrandServicePort;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {
    private final IBrandServicePort brandServicePort;
    private final IBrandResponseMapper brandResponseMapper;
    private final IBrandRequestMapper brandRequestMapper;

    @Operation(summary = "Add a new brand to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request format"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping("/")
    public ResponseEntity<BrandResponse> addBrand(@RequestBody @Valid AddBrandRequest brandRequest) {
        Brand createdBrand = brandServicePort.saveBrand(
                brandRequestMapper.addRequestToBrand(brandRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(brandResponseMapper.toBrandResponse(createdBrand));
    }

    @Operation(summary = "Retrieve all brands.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brands retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/")
    public ResponseEntity<PaginatedResponse<BrandResponse>> getAllBrands(
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer page,
            @Min(value = 1) @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "true") boolean ascendingOrder) {
        return ResponseEntity.ok(brandResponseMapper
                .toPaginatedResponse(brandServicePort.getAllBrands(page, size, ascendingOrder)));
    }
}