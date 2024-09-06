package com.bootcamp_2024_2.api_stock.adapters.driving.http.controller;

import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.CategoryResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.dto.response.PaginatedResponse;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.bootcamp_2024_2.api_stock.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.bootcamp_2024_2.api_stock.domain.api.ICategoryServicePort;
import com.bootcamp_2024_2.api_stock.domain.model.Category;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import com.bootcamp_2024_2.api_stock.testData.CategoryFactory;
import com.bootcamp_2024_2.api_stock.testData.RequestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryRestControllerAdapterTest {
    @Mock
    private ICategoryServicePort categoryServicePort;
    @Mock
    private ICategoryRequestMapper categoryRequestMapper;
    @Mock
    private ICategoryResponseMapper categoryResponseMapper;
    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryRestControllerAdapter).build();
    }

    @Test
    void testGetAllCategories() throws Exception {
        // Given
        int page = 0;
        int size = 10;
        boolean ascendingOrder = true;

        List<Category> categoryList = CategoryFactory.createCategoryList(2);

        Paginate<Category> paginate = new Paginate<>(
                1,
                0,
                2,
                10,
                categoryList
        );

        PaginatedResponse<CategoryResponse> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(categoryList.stream().map(category ->
                new CategoryResponse(category.getId(), category.getName(), category.getDescription())
        ).toList());
        paginatedResponse.setTotalPages(paginate.getTotalPages());
        paginatedResponse.setCurrentPage(paginate.getCurrentPage());
        paginatedResponse.setTotalItems(paginate.getTotalItems());
        paginatedResponse.setPageSize(paginate.getPageSize());

        when(categoryServicePort.getAllCategories(page, size, ascendingOrder)).thenReturn(paginate);
        when(categoryResponseMapper.toPaginatedResponse(paginate)).thenReturn(paginatedResponse);

        // When & Then
        MvcResult mvcResult = mockMvc.perform(get("/category/")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("ascendingOrder", String.valueOf(ascendingOrder))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(paginatedResponse.getTotalPages()))
                .andExpect(jsonPath("$.currentPage").value(paginatedResponse.getCurrentPage()))
                .andExpect(jsonPath("$.totalItems").value(paginatedResponse.getTotalItems()))
                .andExpect(jsonPath("$.pageSize").value(paginatedResponse.getPageSize()))
                .andExpect(jsonPath("$.content[0].id").value(categoryList.get(0).getId()))
                .andExpect(jsonPath("$.content[0].name").value(categoryList.get(0).getName()))
                .andExpect(jsonPath("$.content[0].description").value(categoryList.get(0).getDescription()))
                .andExpect(jsonPath("$.content[1].id").value(categoryList.get(1).getId())) // Validación del segundo objeto
                .andExpect(jsonPath("$.content[1].name").value(categoryList.get(1).getName())) // Validación del segundo objeto
                .andExpect(jsonPath("$.content[1].description").value(categoryList.get(1).getDescription())) // Validación del segundo objeto
                .andReturn();

        verify(categoryServicePort).getAllCategories(page, size, ascendingOrder);
    }

    @ParameterizedTest
    @MethodSource("provideCategoryRequests")
    void testAddCategory(RequestCase testCase) throws Exception {
        // Given
        String requestBody = testCase.getRequestBody();
        HttpStatus expectedStatus = testCase.getExpectedStatus();

        // When & Then
        MvcResult mvcResult = mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is(expectedStatus.value()))
                .andReturn();

        if (expectedStatus == HttpStatus.CREATED) {
            verify(categoryServicePort).saveCategory(any());
        } else if (expectedStatus == HttpStatus.BAD_REQUEST) {
            Exception resolvedException = mvcResult.getResolvedException();
            assertTrue(resolvedException instanceof MethodArgumentNotValidException);
        }
    }

    private static Stream<Arguments> provideCategoryRequests() {
        return Stream.of(
                Arguments.of(generateRequest("{\"name\":\"Electronics\",\"description\":\"Electronics category\"}", HttpStatus.CREATED)),

                Arguments.of(generateRequest("{\"name\":\"\",\"description\":\"Electronics category\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"Electronics\",\"description\":\"\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"A\",\"description\":\"A\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean.\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean.\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"\",\"description\":\"Electronics category\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"Electronics\",\"description\":\"\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"A\",\"description\":\"A\"}", HttpStatus.BAD_REQUEST)),

                Arguments.of(generateRequest("{\"name\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean.\",\"description\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean.\"}", HttpStatus.BAD_REQUEST))
        );
    }

    private static RequestCase generateRequest(String requestBody, HttpStatus expectedStatus) {
        return new RequestCase(requestBody, expectedStatus);
    }
}

