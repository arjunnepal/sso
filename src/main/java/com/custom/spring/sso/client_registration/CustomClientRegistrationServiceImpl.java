package com.custom.spring.sso.client_registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomClientRegistrationServiceImpl implements ClientRegistrationRepository, CustomClientRegistrationService {
    private final CustomClientRegistrationRepository customClientRegistrationRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CustomClientRegistrationServiceImpl(CustomClientRegistrationRepository customClientRegistrationRepository) {
        this.customClientRegistrationRepository = customClientRegistrationRepository;
    }

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        logger.info("find By registrationId: {}", registrationId);
        return customClientRegistrationRepository
                .findById(registrationId).map(CustomClientRegistrationMapper.toClientRegistration)
                .orElse(null);
    }

    @Override
    public List<CustomRegistrationClientResourceMini> getAllMini() {
        logger.info("get all federation clients");
        return customClientRegistrationRepository.findAllMini();
    }
}
