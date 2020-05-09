package com.civelek.Ticket.IService;

import com.civelek.Ticket.Entity.Customer;

import java.util.List;

public interface ICustomerImpl {

    Customer saveCustomer(Customer customer);

    List<Customer> getCustomer(String kimlikNo, String customerName, String customerSurname, String phone);
}
