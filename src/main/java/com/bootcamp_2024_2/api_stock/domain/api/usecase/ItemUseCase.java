package com.bootcamp_2024_2.api_stock.domain.api.usecase;

import com.bootcamp_2024_2.api_stock.domain.api.IItemServicePort;
import com.bootcamp_2024_2.api_stock.domain.exception.DuplicateCategoryException;
import com.bootcamp_2024_2.api_stock.domain.exception.InvalidCategoryListSizeException;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import com.bootcamp_2024_2.api_stock.domain.spi.IItemPersistencePort;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;

import java.util.List;
import java.util.stream.Collectors;

public class ItemUseCase implements IItemServicePort {

    private final IItemPersistencePort itemPersistencePort;

    public ItemUseCase(IItemPersistencePort itemPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public Item saveItem(Item item) {
        validateCategoryListSize(item.getCategoriesList());
        validateUniqueCategories(item.getCategoriesList());

        return itemPersistencePort.saveItem(item);
    }

    @Override
    public Paginate<Item> getAllItems(Integer page, Integer size, boolean ascendingOrder, String order) {
        return itemPersistencePort.getAllItems(page, size, ascendingOrder, order);
    }

    private void validateCategoryListSize(List<Category> categoriesList) {
        int size = categoriesList.size();
        if (size < 1 || size > 3) {
            throw new InvalidCategoryListSizeException(size);
        }
    }

    private void validateUniqueCategories(List<Category> categoriesList) {
        String duplicateIds = categoriesList.stream()
                .collect(Collectors.groupingBy(Category::getId, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> String.valueOf(entry.getKey()))
                .collect(Collectors.joining(", "));

        if (!duplicateIds.isEmpty()) {
            throw new DuplicateCategoryException(duplicateIds);
        }
    }

}
