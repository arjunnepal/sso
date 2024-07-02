package com.custom.spring.sso.consent;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "oauth2_authorization_consents")
@IdClass(CompositeKey.class)
public class CustomAuthorizationConsent {
    @Id
    @Column(nullable = false)
    private String registrationClientId;

    @Id
    @Column(nullable = false)
    private String principalName;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private Set<String> authorities;
}
