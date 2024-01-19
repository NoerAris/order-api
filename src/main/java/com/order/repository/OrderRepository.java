package com.order.repository;

import com.order.entity.TrsOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TrsOrder, Long> {
    @Query("from TrsOrder")
    List<TrsOrder> findAllPageable(Pageable pageable);

    TrsOrder findTop1ById(Long id);
}