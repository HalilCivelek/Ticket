package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Customer;
import com.civelek.Ticket.IService.ICustomerImpl;
import com.civelek.Ticket.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerImpl implements ICustomerImpl {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer newCustomer = new Customer();

        try {

            if (customer == null) {

                throw new IllegalArgumentException("Musteri bilgileri bo gecilemez");
            } else {
                if (customer.getKimlikNo() != null && customerRepository.getByKimlikNo(customer.getKimlikNo()) != null
                        && customer.getKimlikNo().equals(customerRepository.getByKimlikNo(customer.getKimlikNo()).getKimlikNo())) {
                    System.out.println("Musteri zaten kayıtlı.");
                    return null;
                }

                newCustomer.setName(customer.getName());
                newCustomer.setSurname(customer.getSurname());
                newCustomer.setKimlikNo(customer.getKimlikNo());
                newCustomer.setAdress(customer.getAdress());
                newCustomer.setEmail(customer.getEmail());
                newCustomer.setPhone(customer.getPhone());
                newCustomer.setCreatedAt(new Date());
                newCustomer.setStatus(true);
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return customerRepository.save(newCustomer);
    }
}
