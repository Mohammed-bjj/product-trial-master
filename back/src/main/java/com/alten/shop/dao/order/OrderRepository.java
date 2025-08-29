package com.alten.shop.dao.order;

import com.alten.shop.utils.entities.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
