package com.order.service.implement;

import com.order.entity.TrsOrder;
import com.order.service.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplement extends BaseService implements OrderService {
    @Override
    public List<TrsOrder> findAll(Pageable pageable) {
        return orderRepository.findAllPageable(pageable);
    }

    @Override
    public TrsOrder findOne(Long id) {
        return orderRepository.findTop1ById(id);
    }

    @Override
    public void save(TrsOrder trsOrder) {
        orderRepository.save(trsOrder);
    }

    @Override
    public void delete(TrsOrder trsOrder) {
        orderRepository.delete(trsOrder);
    }
}
