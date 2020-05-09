package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

   Customer getByCustomerId(Long id);

  // Customer getByCustomerName(String name);

   Customer getByKimlikNo(String kimlikNo);
}