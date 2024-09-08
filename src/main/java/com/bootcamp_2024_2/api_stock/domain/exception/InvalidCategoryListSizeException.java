package com.bootcamp_2024_2.api_stock.domain.exception;

public class InvalidCategoryListSizeException extends RuntimeException{
    public InvalidCategoryListSizeException(int size) {
        super(String.valueOf(size));
    }
}
