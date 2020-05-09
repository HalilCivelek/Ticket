package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Customer;
import com.civelek.Ticket.IService.ICustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

   @Autowired
   private ICustomerImpl iCustomerImpl;

    /**
     * Musteri kaydeder
     * @param customer
     * @return
     */
    @PostMapping("/saveCustomer")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer){
        return ResponseEntity.ok(iCustomerImpl.saveCustomer(customer));

    }
}
