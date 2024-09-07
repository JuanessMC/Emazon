package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.ItemEntity;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-06T19:47:28-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class IItemEntityMapperImpl implements IItemEntityMapper {

    @Override
    public ItemEntity toEntity(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setBrand( itemToBrandEntity( item ) );
        itemEntity.setCategoriesList( categoryListToCategoryEntitySet( item.getCategoriesList() ) );
        itemEntity.setId( item.getId() );
        itemEntity.setName( item.getName() );
        itemEntity.setDescription( item.getDescription() );
        itemEntity.setQuantity( item.getQuantity() );
        itemEntity.setPrice( BigDecimal.valueOf( item.getPrice() ) );

        return itemEntity;
    }

    @Override
    public Item toModel(ItemEntity itemEntity) {
        if ( itemEntity == null ) {
            return null;
        }

        List<Category> categoriesList = null;
        Long id = null;
        String name = null;
        String description = null;
        int quantity = 0;
        float price = 0.0f;

        categoriesList = categoryEntitySetToCategoryList( itemEntity.getCategoriesList() );
        id = itemEntity.getId();
        name = itemEntity.getName();
        description = itemEntity.getDescription();
        quantity = itemEntity.getQuantity();
        if ( itemEntity.getPrice() != null ) {
            price = itemEntity.getPrice().floatValue();
        }

        Long idBrand = null;

        Item item = new Item( id, name, description, quantity, price, idBrand, categoriesList );

        return item;
    }

    @Override
    public List<Item> toModelList(List<ItemEntity> itemEntities) {
        if ( itemEntities == null ) {
            return null;
        }

        List<Item> list = new ArrayList<Item>( itemEntities.size() );
        for ( ItemEntity itemEntity : itemEntities ) {
            list.add( toModel( itemEntity ) );
        }

        return list;
    }

    protected BrandEntity itemToBrandEntity(Item item) {
        if ( item == null ) {
            return null;
        }

        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId( item.getIdBrand() );

        return brandEntity;
    }

    protected CategoryEntity categoryToCategoryEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( category.getId() );
        categoryEntity.setName( category.getName() );
        categoryEntity.setDescription( category.getDescription() );

        return categoryEntity;
    }

    protected Set<CategoryEntity> categoryListToCategoryEntitySet(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        Set<CategoryEntity> set = new LinkedHashSet<CategoryEntity>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Category category : list ) {
            set.add( categoryToCategoryEntity( category ) );
        }

        return set;
    }

    protected Category categoryEntityToCategory(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = categoryEntity.getId();
        name = categoryEntity.getName();
        description = categoryEntity.getDescription();

        Category category = new Category( id, name, description );

        return category;
    }

    protected List<Category> categoryEntitySetToCategoryList(Set<CategoryEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( set.size() );
        for ( CategoryEntity categoryEntity : set ) {
            list.add( categoryEntityToCategory( categoryEntity ) );
        }

        return list;
    }
}
