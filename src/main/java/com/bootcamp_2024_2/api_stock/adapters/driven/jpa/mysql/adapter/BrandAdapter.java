package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.adapter;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new ElementAlreadyExistsException(brand.getName());
        }

        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public List<Brand> getAllBlands(Integer page, Integer size, boolean ascendingOrder) {
        Pageable pagination = PageRequest.of(page, size, ascendingOrder ? Sort.by("name").ascending() : Sort.by("name").descending());
        return brandEntityMapper.toModelList(brandRepository.findAll(pagination).getContent());
    }
}
