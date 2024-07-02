package com.custom.spring.sso.consent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthorizationConsentService implements OAuth2AuthorizationConsentService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CustomAuthorizationConsentRepository customAuthorizationConsentRepository;

    public CustomAuthorizationConsentService(CustomAuthorizationConsentRepository customAuthorizationConsentRepository) {
        this.customAuthorizationConsentRepository = customAuthorizationConsentRepository;
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        logger.info("Saving authorization consent {}", authorizationConsent);
        this.customAuthorizationConsentRepository
                .save(CustomAuthorizationConsentMapper
                        .authConsentToEntity.apply(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        logger.info("Removing authorization consent {}", authorizationConsent);
        this.customAuthorizationConsentRepository
                .deleteById(new CompositeKey(authorizationConsent.getRegisteredClientId(),
                        authorizationConsent.getPrincipalName()));
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        logger.info("Finding authorization consent by registered client {} ,principal {}", registeredClientId, principalName);
        return this.customAuthorizationConsentRepository
                .findById(new CompositeKey(registeredClientId, principalName))
                .map(CustomAuthorizationConsentMapper.entityToAuthConsent).orElse(null);
    }
}
