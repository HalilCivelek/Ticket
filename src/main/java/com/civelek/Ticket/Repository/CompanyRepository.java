package com.civelek.Ticket.Repository;

import com.civelek.Ticket.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository  extends JpaRepository<Company,Long> {

    Company getByCompanyId(Long id);

    Company getByCompanyName(String companyName);

    @Override
    List<Company> findAll();
}
