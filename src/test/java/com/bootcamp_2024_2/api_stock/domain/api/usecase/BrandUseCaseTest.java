package com.bootcamp_2024_2.api_stock.domain.api.usecase;

import org.junit.jupiter.api.Test;
import com.bootcamp_2024_2.api_stock.domain.model.Brand;
import com.bootcamp_2024_2.api_stock.domain.spi.IBrandPersistencePort;
import com.bootcamp_2024_2.api_stock.testData.BrandFactory;
import org.junit.jupiter.api.DisplayName;
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
class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    @DisplayName("Given a brand, it must be inserted into the database.")
    void saveBrand() {
        // GIVEN
        Brand brand = BrandFactory.createBrand();
        doNothing().when(brandPersistencePort).saveBrand(brand);

        // WHEN
        brandUseCase.saveBrand(brand);

        // THEN
        verify(brandPersistencePort, times(1)).saveBrand(brand);
    }
}