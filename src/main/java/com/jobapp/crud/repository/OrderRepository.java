package com.jobapp.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobapp.crud.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
