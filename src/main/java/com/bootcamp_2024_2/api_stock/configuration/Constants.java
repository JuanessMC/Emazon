package com.bootcamp_2024_2.api_stock.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }
    public static final String INVALID_FIELD = "invalid field";
    public static final String ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The element with name %s that you want to create already exists";
    public static final String DUPLICATE_CATEGORY = "Duplicate category IDs found: %S";
    public static final String INVALID_CATEGORY_LIST_SIZE_MESSAGE = "The number of categories must be between 1 and 3, but found: %d";

}
