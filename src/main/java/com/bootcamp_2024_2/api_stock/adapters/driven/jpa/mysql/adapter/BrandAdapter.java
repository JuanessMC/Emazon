package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.adapter;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.spi.IBrandPersistencePort;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public Brand saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntity(brand));

        return brand;
    }

    @Override
    public Paginate<Brand> getAllBrands(Integer page, Integer size, boolean ascendingOrder) {
        Sort.Direction direction = ascendingOrder ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, "name"));

        Page<BrandEntity> brandPage = brandRepository.findAll(pageRequest);

        List<Brand> brands = brandEntityMapper.toModelList(brandPage.getContent());

        return Paginate.of(
                brandPage.getTotalPages(),
                brandPage.getNumber(),
                (int) brandPage.getTotalElements(),
                brandPage.getSize(),
                brands
        );
    }


    @Override
    public boolean existsByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }
}
