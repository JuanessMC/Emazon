package com.bootcamp_2024_2.api_stock.domain.spi;

import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;

import java.util.List;

public interface IBrandPersistencePort {
    Brand saveBrand(Brand brand);
    Paginate<Brand> getAllBrands(Integer page, Integer size, boolean ascendingOrder);
    boolean existsByName(String name);
}
