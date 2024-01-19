package com.order.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "trs_order")
@Data
public class TrsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "orderNo is required")
    @Column(name = "order_no", length = 100)
    private String orderNo;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Item item;

    @Column(name = "qty")
    private int qty;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idItem;
}
