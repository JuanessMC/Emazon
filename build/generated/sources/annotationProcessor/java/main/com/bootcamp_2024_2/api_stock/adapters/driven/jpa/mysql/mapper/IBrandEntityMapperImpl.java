package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
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
public class IBrandEntityMapperImpl implements IBrandEntityMapper {

    @Override
    public Brand toModel(BrandEntity brandEntity) {
        if ( brandEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = brandEntity.getId();
        name = brandEntity.getName();
        description = brandEntity.getDescription();

        Brand brand = new Brand( id, name, description );

        return brand;
    }

    @Override
    public BrandEntity toEntity(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId( brand.getId() );
        brandEntity.setName( brand.getName() );
        brandEntity.setDescription( brand.getDescription() );

        return brandEntity;
    }

    @Override
    public List<Brand> toModelList(List<BrandEntity> brandEntities) {
        if ( brandEntities == null ) {
            return null;
        }

        List<Brand> list = new ArrayList<Brand>( brandEntities.size() );
        for ( BrandEntity brandEntity : brandEntities ) {
            list.add( toModel( brandEntity ) );
        }

        return list;
    }
}
