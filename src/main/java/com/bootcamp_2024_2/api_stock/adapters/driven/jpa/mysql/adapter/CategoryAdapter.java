package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.adapter;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new ElementAlreadyExistsException(category.getName());
        }

        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size, boolean ascendingOrder) {
        Pageable pagination = PageRequest.of(page, size, ascendingOrder ? Sort.by("name").ascending() : Sort.by("name").descending());
        return categoryEntityMapper.toModelList(categoryRepository.findAll(pagination).getContent());
    }
}
