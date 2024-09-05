package com.bootcamp_2024_2.api_stock.domain.spi;

import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;

import java.util.List;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);

    Paginate<Category> getAllCategories(Integer page, Integer size, boolean ascendingOrder);

    boolean existsByName(String name);
}
