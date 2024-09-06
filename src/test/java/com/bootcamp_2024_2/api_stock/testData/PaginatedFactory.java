package com.bootcamp_2024_2.api_stock.testData;

import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import java.util.List;
import java.util.Random;


public class PaginatedFactory {
    private static final Random random = new Random();

    public static <T> Paginate<T> createPaginatedResult(List<T> items, int page, int size) {
        int totalItems = items.size();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return Paginate.of(totalPages, page, totalItems, size, items);
    }

    public static Paginate<Category> createPaginatedCategories() {
        int page = random.nextInt(10) + 1;
        int size = random.nextInt(10) + 1;
        List<Category> categories = CategoryFactory.createCategoryList(size);
        return createPaginatedResult(categories, page, size);
    }

    public static Paginate<Brand> createPaginatedBrands() {
        int page = random.nextInt(10) + 1;
        int size = random.nextInt(10) + 1;
        List<Brand> brands = BrandFactory.createBrandList(size);
        return createPaginatedResult(brands, page, size);
    }

}
