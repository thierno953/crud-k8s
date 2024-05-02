package com.jobapp.crud.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobapp.crud.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
