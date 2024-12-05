package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddCategoryRequest;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-10T20:28:05-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class ICategoryRequestMapperImpl implements ICategoryRequestMapper {

    @Override
    public Category addRequestToCategory(AddCategoryRequest addCategoryRequest) {
        if ( addCategoryRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = addCategoryRequest.name();
        description = addCategoryRequest.description();

        Long id = null;

        Category category = new Category( id, name, description );

        return category;
    }
}
