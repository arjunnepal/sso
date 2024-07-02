package com.custom.spring.sso.client_registration;

import com.custom.spring.sso.common.AbstractEntity;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "custom_client_registrations")
public class CustomClientRegistration extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String clientId;
    @Column(nullable = false)
    private String clientSecret;
    @Column(nullable = false)

    private String clientAuthenticationMethod;
    @Column(nullable = false)

    private String authorizationGrantType;
    @Column(nullable = false)

    private String redirectUri;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private Set<String> scopes;

    @Column(unique = true, nullable = false)
    private String clientName;

    private String providerAuthorizationUri;
    private String providerTokenUri;
    private String providerUserInfoUri;
    private String providerUserInfoAuthenticationMethod;
    private String providerUserNameAttributeName;
    private String providerJwkSetUri;
    private String providerIssuerUri;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> providerConfigurationMetadata;
}
