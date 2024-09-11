package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.request.AddBrandRequest;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-10T20:28:05-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class IBrandRequestMapperImpl implements IBrandRequestMapper {

    @Override
    public Brand addRequestToBrand(AddBrandRequest addBrandRequest) {
        if ( addBrandRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = addBrandRequest.name();
        description = addBrandRequest.description();

        Long id = null;

        Brand brand = new Brand( id, name, description );

        return brand;
    }
}
