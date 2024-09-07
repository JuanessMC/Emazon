package com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.BrandResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-06T19:37:28-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class IBrandResponseMapperImpl implements IBrandResponseMapper {

    @Override
    public PaginatedResponse<BrandResponse> toPaginatedResponse(Paginate<Brand> paginatedResult) {
        if ( paginatedResult == null ) {
            return null;
        }

        PaginatedResponse<BrandResponse> paginatedResponse = new PaginatedResponse<BrandResponse>();

        paginatedResponse.setTotalPages( paginatedResult.getTotalPages() );
        paginatedResponse.setCurrentPage( paginatedResult.getCurrentPage() );
        paginatedResponse.setTotalItems( paginatedResult.getTotalItems() );
        paginatedResponse.setPageSize( paginatedResult.getPageSize() );
        paginatedResponse.setContent( toBrandResponseList( paginatedResult.getContent() ) );

        return paginatedResponse;
    }

    @Override
    public BrandResponse toBrandResponse(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = brand.getId();
        name = brand.getName();
        description = brand.getDescription();

        BrandResponse brandResponse = new BrandResponse( id, name, description );

        return brandResponse;
    }

    @Override
    public List<BrandResponse> toBrandResponseList(List<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandResponse> list = new ArrayList<BrandResponse>( brands.size() );
        for ( Brand brand : brands ) {
            list.add( toBrandResponse( brand ) );
        }

        return list;
    }
}
