package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.adapter;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public Category saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));

        return category;
    }

    @Override
    public Paginate<Category> getAllCategories(Integer page, Integer size, boolean ascendingOrder) {
        Sort.Direction direction = ascendingOrder ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, "name"));

        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);
        List<Category> categories = categoryEntityMapper.toModelList(categoryPage.getContent());

        return Paginate.of(
                categoryPage.getTotalPages(),
                categoryPage.getNumber(),
                (int) categoryPage.getTotalElements(),
                categoryPage.getSize(),
                categories);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }
}
