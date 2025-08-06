package com.monocart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocart.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
