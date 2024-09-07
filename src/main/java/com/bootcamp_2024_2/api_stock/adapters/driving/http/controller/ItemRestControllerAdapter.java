package com.bootcamp_2024_2.api_stock.adapters.driving.http.controller;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddItemRequest;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.ItemResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.IItemRequestMapper;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.IItemResponseMapper;
import com.bootcamp_2024_2.api_stock.domain.api.IItemServicePort;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemRestControllerAdapter {
    private final IItemServicePort itemServicePort;
    private final IItemRequestMapper itemRequestMapper;
    private final IItemResponseMapper itemResponseMapper;

    @Operation(summary = "Add a new item to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request format"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping("/")
    public ResponseEntity<ItemResponse> addItem(@RequestBody @Valid AddItemRequest itemRequest) {
        // Convertir el request DTO a modelo de dominio
        Item createdItem = itemServicePort.saveItem(
                itemRequestMapper.addRequestToItem(itemRequest));

        // Convertir el modelo de dominio a DTO de respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseMapper.toItemResponse(createdItem));
    }
}
