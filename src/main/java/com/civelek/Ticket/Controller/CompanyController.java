package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.IService.ICompanyImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private  ICompanyImpl iCompanyImpl;

    @PostMapping("/saveCompany")
    public ResponseEntity<Company> saveCustomer(@Valid @RequestBody Company company){
        return ResponseEntity.ok(iCompanyImpl.saveCompany(company));

    }

    /**
     * Tüm sirketleri döndürür.
     * @return
     */
    @GetMapping("/getCompanyList")
    public JSONObject getCompanyList(){
        JSONObject sendJson = new JSONObject();
        sendJson =  iCompanyImpl.getCompanyList();
        return sendJson;
    }

    /**
     *
     * @param companyName
     * @return
     */
    @GetMapping("/getCompany")
    @ResponseBody
    public ResponseEntity<Company> getCompanyByCompanyName(@RequestParam String companyName){
        Company company =  iCompanyImpl.getCompany(companyName);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }


}
