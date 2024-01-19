package com.order.service;

import com.order.entity.Item;
import com.order.entity.TrsOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ItemService {
    List<Item> findAll(Pageable pageable);
    Item findOne(Long id);
    void save (Item item);
    void delete (Item item);
}
