package com.bootcamp_2024_2.api_stock.domain.spi;

import com.bootcamp_2024_2.api_stock.domain.model.Item;
import com.bootcamp_2024_2.api_stock.domain.util.Paginate;

public interface IItemPersistencePort {
    Item saveItem(Item item);

    Paginate<Item> getAllItems(Integer page, Integer size, boolean ascendingOrder, String order);

}