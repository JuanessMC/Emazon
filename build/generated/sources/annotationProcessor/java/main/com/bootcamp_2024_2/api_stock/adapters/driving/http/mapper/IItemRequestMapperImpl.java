package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddCategoryByItemRequest;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddItemRequest;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-07T22:22:22-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class IItemRequestMapperImpl implements IItemRequestMapper {

    @Override
    public Item addRequestToItem(AddItemRequest addItemRequest) {
        if ( addItemRequest == null ) {
            return null;
        }

        List<Category> categoriesList = null;
        String name = null;
        String description = null;
        int quantity = 0;
        float price = 0.0f;
        Long idBrand = null;

        categoriesList = addCategoryByItemRequestListToCategoryList( addItemRequest.categoriesIdList() );
        name = addItemRequest.name();
        description = addItemRequest.description();
        quantity = addItemRequest.quantity();
        price = addItemRequest.price();
        idBrand = addItemRequest.idBrand();

        Long id = null;

        Item item = new Item( id, name, description, quantity, price, idBrand, categoriesList );

        return item;
    }

    protected Category addCategoryByItemRequestToCategory(AddCategoryByItemRequest addCategoryByItemRequest) {
        if ( addCategoryByItemRequest == null ) {
            return null;
        }

        Long id = null;

        id = addCategoryByItemRequest.id();

        String name = null;
        String description = null;

        Category category = new Category( id, name, description );

        return category;
    }

    protected List<Category> addCategoryByItemRequestListToCategoryList(List<AddCategoryByItemRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Category> list1 = new ArrayList<Category>( list.size() );
        for ( AddCategoryByItemRequest addCategoryByItemRequest : list ) {
            list1.add( addCategoryByItemRequestToCategory( addCategoryByItemRequest ) );
        }

        return list1;
    }
}
