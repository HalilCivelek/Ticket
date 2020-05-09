package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Customer;
import com.civelek.Ticket.Entity.Route;
import com.civelek.Ticket.IService.ICustomerImpl;
import com.civelek.Ticket.util.VTUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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

    @GetMapping("/getCustomer")
    @ResponseBody
    public void getCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String kimlikNo = VTUtil.reqGetString(request.getParameter("kimlikNo"),null);
        String customerName = VTUtil.reqGetString(request.getParameter("customerName"),null);
        String customerSurname = VTUtil.reqGetString(request.getParameter("customerSurname"),null);
        String phone = VTUtil.reqGetString(request.getParameter("phone"),null);


        JSONObject sendJson = new JSONObject();
        List<Customer> customers = iCustomerImpl.getCustomer(kimlikNo, customerName, customerSurname, phone);

        sendJson.put("customers",customers);
        response.getWriter().write(sendJson.toString());
    }
}
