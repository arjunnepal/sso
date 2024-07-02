package com.custom.spring.sso.registered_client;

import com.custom.spring.sso.common.AbstractRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomRegisteredClientRepository extends AbstractRepository<CustomRegisteredClient> {
    Optional<CustomRegisteredClient> findByClientId(String clientId);

    @Query("select rc.originUris from CustomRegisteredClient rc")
    List<List<String>> findAllOriginUrls();

}
