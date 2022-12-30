package com.hibernate.app.repo;

import com.hibernate.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findByUserName(@Param("userName") String userName);

    Customer findByEmail(@Param("email") String email);

    Optional<Customer> findByCustomerId(double customerId);
}
