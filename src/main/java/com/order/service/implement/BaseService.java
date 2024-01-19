package com.order.service.implement;

import com.order.repository.ItemRepository;
import com.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;
}
