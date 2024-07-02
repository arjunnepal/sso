package com.custom.spring.sso.client_registration;

import com.custom.spring.sso.common.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomClientRegistrationRepository extends AbstractRepository<CustomClientRegistration> {

    @Query("select new com.custom.spring.sso.client_registration.CustomRegistrationClientResourceMini(ccr.id,ccr.clientName) from CustomClientRegistration ccr")
    List<CustomRegistrationClientResourceMini> findAllMini();
}
