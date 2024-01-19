package com.order.service;

import com.order.entity.TrsOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService {
    List<TrsOrder> findAll(Pageable pageable);
    TrsOrder findOne(Long id);
    void save (TrsOrder trsOrder);
    void delete (TrsOrder trsOrder);
}
