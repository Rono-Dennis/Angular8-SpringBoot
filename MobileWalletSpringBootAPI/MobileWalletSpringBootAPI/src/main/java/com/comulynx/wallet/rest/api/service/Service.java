package com.comulynx.wallet.rest.api.service;

import com.comulynx.wallet.rest.api.model.Customer;
import com.comulynx.wallet.rest.api.model.Webuser;
import com.comulynx.wallet.rest.api.repository.CustomerRepository;
import com.comulynx.wallet.rest.api.repository.WebuserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private WebuserRepository webuserRepository;



    public Webuser fetchEmployeeIdAndPassword(String tempid, String tempPass) {
        return webuserRepository.findByEmployeeIdAndPassword(tempid,tempPass);
    }
}
