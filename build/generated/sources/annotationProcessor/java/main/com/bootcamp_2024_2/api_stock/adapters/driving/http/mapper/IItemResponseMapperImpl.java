package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.CategoryByItemResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.ItemResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-10T20:28:05-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class IItemResponseMapperImpl implements IItemResponseMapper {

    @Override
    public PaginatedResponse<ItemResponse> toPaginatedResponse(Paginate<Item> paginatedResult) {
        if ( paginatedResult == null ) {
            return null;
        }

        PaginatedResponse<ItemResponse> paginatedResponse = new PaginatedResponse<ItemResponse>();

        paginatedResponse.setTotalPages( paginatedResult.getTotalPages() );
        paginatedResponse.setCurrentPage( paginatedResult.getCurrentPage() );
        paginatedResponse.setTotalItems( paginatedResult.getTotalItems() );
        paginatedResponse.setPageSize( paginatedResult.getPageSize() );
        paginatedResponse.setContent( toItemResponseList( paginatedResult.getContent() ) );

        return paginatedResponse;
    }

    @Override
    public ItemResponse toItemResponse(Item item) {
        if ( item == null ) {
            return null;
        }

        List<CategoryByItemResponse> categoriesList = null;
        Long idBrand = null;
        String brandName = null;
        Long id = null;
        String name = null;
        String description = null;
        int quantity = 0;
        float price = 0.0f;

        categoriesList = categoryListToCategoryByItemResponseList( item.getCategoriesList() );
        idBrand = itemBrandId( item );
        brandName = itemBrandName( item );
        id = item.getId();
        name = item.getName();
        description = item.getDescription();
        quantity = item.getQuantity();
        price = item.getPrice();

        ItemResponse itemResponse = new ItemResponse( id, name, description, quantity, price, idBrand, brandName, categoriesList );

        return itemResponse;
    }

    @Override
    public List<ItemResponse> toItemResponseList(List<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemResponse> list = new ArrayList<ItemResponse>( items.size() );
        for ( Item item : items ) {
            list.add( toItemResponse( item ) );
        }

        return list;
    }

    protected CategoryByItemResponse categoryToCategoryByItemResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        long id = 0L;
        String name = null;

        if ( category.getId() != null ) {
            id = category.getId();
        }
        name = category.getName();

        CategoryByItemResponse categoryByItemResponse = new CategoryByItemResponse( id, name );

        return categoryByItemResponse;
    }

    protected List<CategoryByItemResponse> categoryListToCategoryByItemResponseList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryByItemResponse> list1 = new ArrayList<CategoryByItemResponse>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryByItemResponse( category ) );
        }

        return list1;
    }

    private Long itemBrandId(Item item) {
        if ( item == null ) {
            return null;
        }
        Brand brand = item.getBrand();
        if ( brand == null ) {
            return null;
        }
        Long id = brand.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String itemBrandName(Item item) {
        if ( item == null ) {
            return null;
        }
        Brand brand = item.getBrand();
        if ( brand == null ) {
            return null;
        }
        String name = brand.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
