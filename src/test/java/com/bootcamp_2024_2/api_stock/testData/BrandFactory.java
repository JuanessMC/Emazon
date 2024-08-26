package com.bootcamp_2024_2.api_stock.testData;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.BrandResponse;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BrandFactory {
    private static final Random random = new Random();

    public static Brand createBrand() {
        String name = getRandomBrandName();
        String description = "Description for " + name;
        return new Brand(null, name, description);
    }

    private static String getRandomBrandName() {
        String[] brandNames = new String[] { "Sony", "Nike", "Apple", "Samsung", "Adidas", "Toyota", "L'Oréal", "Nestlé", "Amazon", "Microsoft" };
        return brandNames[random.nextInt(brandNames.length)];
    }

    public static List<Brand> createBrandList(int count) {
        List<Brand> brands = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            brands.add(new Brand((long)i, getRandomBrandName(), null));
        }
        return brands;
    }


}
