package com.ms.order_service.repository;

import com.ms.order_service.entiry.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
