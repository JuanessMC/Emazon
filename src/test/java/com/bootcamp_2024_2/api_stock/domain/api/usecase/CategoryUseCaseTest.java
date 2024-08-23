package com.bootcamp_2024_2.api_stock.domain.api.usecase;

import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp_2024_2.api_stock.testData.CategoryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    @DisplayName("Given a category, it must be inserted into the database.")
    void saveCategory() {
        //GIVEN
        Category category = CategoryFactory.createCategory();
        doNothing().when(categoryPersistencePort).saveCategory(category);

        //WHEN
        categoryUseCase.saveCategory(category);

        //THEN
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }
    @Test
    void getAllCategories() {
        // GIVEN
        Integer page = 0;
        Integer size = 10;
        boolean ascendingOrder = true;
        List<Category> expectedCategories = Arrays.asList(
                CategoryFactory.createCategory(),
                CategoryFactory.createCategory()
        );
        when(categoryPersistencePort.getAllCategories(page, size, ascendingOrder)).thenReturn(expectedCategories);

        // WHEN
        List<Category> actualCategories = categoryUseCase.getAllCategories(page, size, ascendingOrder);

        // THEN
        assertEquals(expectedCategories.size(), actualCategories.size());
        assertTrue(expectedCategories.containsAll(actualCategories));
        verify(categoryPersistencePort, times(1)).getAllCategories(page, size, ascendingOrder);
    }

}