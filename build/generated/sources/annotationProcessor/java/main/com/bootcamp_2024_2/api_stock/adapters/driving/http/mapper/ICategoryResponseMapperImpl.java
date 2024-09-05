package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.CategoryResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-04T19:58:57-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ICategoryResponseMapperImpl implements ICategoryResponseMapper {

    @Override
    public PaginatedResponse<CategoryResponse> toPaginatedResponse(Paginate<Category> paginatedResult) {
        if ( paginatedResult == null ) {
            return null;
        }

        List<CategoryResponse> content = null;
        Integer totalPages = null;
        Integer currentPage = null;
        Integer totalItems = null;
        Integer pageSize = null;

        content = toCategoryResponseList( paginatedResult.getContent() );
        totalPages = paginatedResult.getTotalPages();
        currentPage = paginatedResult.getCurrentPage();
        totalItems = paginatedResult.getTotalItems();
        pageSize = paginatedResult.getPageSize();

        PaginatedResponse<CategoryResponse> paginatedResponse = new PaginatedResponse<CategoryResponse>( totalPages, currentPage, totalItems, pageSize, content );

        return paginatedResponse;
    }

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, name, description );

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( Category category : categories ) {
            list.add( toCategoryResponse( category ) );
        }

        return list;
    }
}
