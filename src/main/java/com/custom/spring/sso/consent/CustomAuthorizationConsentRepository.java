package com.custom.spring.sso.consent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomAuthorizationConsentRepository extends JpaRepository<CustomAuthorizationConsent, CompositeKey> {
}
