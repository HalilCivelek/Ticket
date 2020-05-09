package com.civelek.Ticket.IService;

import com.civelek.Ticket.Entity.Company;
import net.minidev.json.JSONObject;

public interface ICompanyImpl {

    /**
     * Sirket bilgilerini kaydeder
     * @param company
     * @return
     */
    Company saveCompany(Company company);

    /**
     * Sirket bilgilerini geri döner
     * @return
     */
    JSONObject getCompanyList();

    /**
     * İsme göre sirket bilgisini dondurur.
     * @param companyName
     * @return
     */
    Company getCompany(String companyName);
}
