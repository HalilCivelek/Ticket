package com.civelek.Ticket.Service;

import com.civelek.Ticket.Entity.Company;
import com.civelek.Ticket.IService.ICompanyImpl;
import com.civelek.Ticket.Repository.CompanyRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyImpl implements ICompanyImpl {

    @Autowired
    private CompanyRepository companyRepository;


    /**
     * Havayollarını kaydeder
     * @param company
     * @return
     */
    @Override
    public Company saveCompany(Company company) {
        Company newCompany = new Company();

       try{
         if(company == null ){

             throw  new IllegalArgumentException("Musteri bilgileri boş olamaz");
         }else if(company != null && company.getCompanyName() != null){

             if( companyRepository.getByCompanyName(company.getCompanyName()) != null
                     && company.getCompanyName().equals(companyRepository.getByCompanyName(company.getCompanyName()).getCompanyName())){
                 System.out.println("Bu havayolu daha önce kaydedilmis.Luften başka bir havayolu kaydediniz.");
                 return null;

             }
             newCompany.setCompanyName(company.getCompanyName());
             newCompany.setCreatedAt(new Date());
             newCompany.setStatus(true);

         }

       }catch (Exception e){
           e.getMessage();
       }

        return companyRepository.save(newCompany);
    }

    /**
     * Sirket bilgilerini dondurur
     * @return
     */
    @Override
    public JSONObject getCompanyList() {
        List<Company> company = companyRepository.findAll();
        JSONObject json = new JSONObject();
        json.put("data",company);
        return json;
    }


    @Override
    public Company getCompany(String companyName){
        Company company = null;
        if (companyName != null && !companyName.equalsIgnoreCase("")) {
            company = companyRepository.getByCompanyName(companyName);
        }

        return company;
    }
}
