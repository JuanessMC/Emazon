package com.bootcamp_2024_2.api_stock.adapters.driving.http.controller;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddItemRequest;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.ItemResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.IItemRequestMapper;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.IItemResponseMapper;
import com.bootcamp_2024_2.api_stock.domain.api.IItemServicePort;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        Item createdItem = itemServicePort.saveItem(
                itemRequestMapper.addRequestToItem(itemRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponseMapper.toItemResponse(createdItem));
    }

    @Operation(summary = "Retrieve all items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping("/")
    public ResponseEntity<PaginatedResponse<ItemResponse>> getAllItems(
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer page,
            @Min(value = 1) @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "true") boolean ascendingOrder,
            @RequestParam(required = false, defaultValue = "itemName") String order) {


        PaginatedResponse<ItemResponse> paginatedResponse = itemResponseMapper.toPaginatedResponse(
                itemServicePort.getAllItems(page, size, ascendingOrder, order));

        return ResponseEntity.ok(paginatedResponse);
    }


}
