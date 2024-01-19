package com.order.repository;

import com.order.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("from Item")
    List<Item> findAllPageable(Pageable pageable);

    Item findTop1ById(Long id);
}
