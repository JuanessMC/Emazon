package com.bootcamp_2024_2.api_stock.domain.spi;

import com.bootcamp_2024_2.api_stock.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {
    void saveBland(Brand brand);
}
