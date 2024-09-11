package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.ItemResponse;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IItemResponseMapper extends IGenericResponseMapper<ItemResponse, Item>{
    @Mapping(source = "categoriesList", target = "categoriesList")
    @Mapping(source = "brand.id", target = "idBrand")
    @Mapping(source = "brand.name", target = "brandName")
    ItemResponse toItemResponse(Item item);

    List<ItemResponse> toItemResponseList(List<Item> items);
}
