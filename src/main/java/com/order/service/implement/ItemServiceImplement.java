package com.order.service.implement;

import com.order.entity.Item;
import com.order.service.ItemService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImplement extends BaseService implements ItemService {
    @Override
    public List<Item> findAll(Pageable pageable) {
        return itemRepository.findAllPageable(pageable);
    }

    @Override
    public Item findOne(Long id) {
        return itemRepository.findTop1ById(id);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
