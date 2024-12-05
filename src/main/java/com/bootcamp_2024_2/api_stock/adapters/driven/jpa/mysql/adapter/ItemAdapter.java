package com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.adapter;

import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.entity.ItemEntity;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.mapper.IItemEntityMapper;
import com.bootcamp_2024_2.api_stock.adapters.driven.jpa.mysql.repository.IItemRepository;
import com.bootcamp_2024_2.api_stock.domain.model.Item;
import com.bootcamp_2024_2.api_stock.domain.spi.IItemPersistencePort;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class ItemAdapter implements IItemPersistencePort {

    private final IItemRepository itemRepository;
    private final IItemEntityMapper itemEntityMapper;

    @Override
    public Item saveItem(Item item) {
        itemRepository.save(itemEntityMapper.toEntity(item));

        return item;
    }

    @Override
    public Paginate<Item> getAllItems(Integer page, Integer size, boolean ascendingOrder, String order) {
        Pageable pagination = PageRequest.of(page, size, determineSort(order, ascendingOrder));

        Page<ItemEntity> itemPage;
        if ("quantity".equalsIgnoreCase(order)) {
            itemPage = itemRepository.findAllOrderByCategories(pagination);
        } else {
            itemPage = itemRepository.findAll(pagination);
        }

        List<Item> items = itemEntityMapper.toModelList(itemPage.getContent());

        return Paginate.of(
                itemPage.getTotalPages(),
                itemPage.getNumber(),
                (int) itemPage.getTotalElements(),
                itemPage.getSize(),
                items
        );
    }

    private Sort determineSort(String order, boolean ascendingOrder) {
        Sort.Direction direction = ascendingOrder ? Sort.Direction.ASC : Sort.Direction.DESC;

        switch (order.toLowerCase()) {
            case "quantity":
                return Sort.unsorted();
            case "brandname":
                return Sort.by(direction, "brand.name");
            default:
                return Sort.by(direction, "name");
        }
    }

}



