package org.example.repository;

import org.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
}
