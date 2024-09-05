package com.bootcamp_2024_2.api_stock.domain.api;

import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;

import java.util.List;

public interface IBrandServicePort {
    Brand saveBrand(Brand brand);

    Paginate<Brand> getAllBrands(Integer page, Integer size, boolean ascendingOrder);
}
