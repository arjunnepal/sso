package com.custom.spring.sso.registered_client;

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

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "oauth2_registered_clients")
public class CustomRegisteredClient extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String clientId;
    private Instant clientIdIssuedAt;
    @Column(nullable = false)
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    @Column(unique = true, nullable = false)
    private String clientName;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]", nullable = false)
    private List<String> clientAuthenticationMethods;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]", nullable = false)
    private List<String> authorizationGrantTypes;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]", nullable = false)
    private Set<String> redirectUris;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private Set<String> postLogoutRedirectUris;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private Set<String> originUris;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]", nullable = false)
    private Set<String> scopes;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> clientSettings;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, Object> tokenSettings;
}