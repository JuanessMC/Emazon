package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.repository;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IItemRepository extends JpaRepository<ItemEntity, Long> {
    @Query("SELECT c FROM ItemEntity c LEFT JOIN c.categoriesList t GROUP BY c " +
            "ORDER BY COUNT(t) ASC")
    Page<ItemEntity> findAllOrderByCategories(Pageable pageable);


}
