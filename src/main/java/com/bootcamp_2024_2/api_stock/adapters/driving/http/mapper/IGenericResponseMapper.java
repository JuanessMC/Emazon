package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;

public interface IGenericResponseMapper<T, R> {
    PaginatedResponse<T> toPaginatedResponse(Paginate<R> paginatedResult);
}