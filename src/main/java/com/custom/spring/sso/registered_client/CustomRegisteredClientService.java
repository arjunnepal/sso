package com.custom.spring.sso.registered_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomRegisteredClientService implements RegisteredClientRepository {

    private final CustomRegisteredClientRepository customRegisteredClientRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CustomRegisteredClientService(CustomRegisteredClientRepository customRegisteredClientRepository) {
        this.customRegisteredClientRepository = customRegisteredClientRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        logger.info("Saving registered client {}", registeredClient);
        this.customRegisteredClientRepository.save(CustomRegisteredClientMapper
                .registeredClientToEntity
                .apply(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        logger.info("Finding registered client {}", id);
        return this.customRegisteredClientRepository.findById(id)
                .map(CustomRegisteredClientMapper
                        .toRegisteredClient)
                .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        logger.info("Finding registered clientId {}", clientId);
        return this.customRegisteredClientRepository.findByClientId(clientId)
                .map(CustomRegisteredClientMapper
                        .toRegisteredClient)
                .orElse(null);
    }

    public List<String> findAllOriginUrls() {
        logger.info("findAllOriginUrls");
        return this.customRegisteredClientRepository.findAllOriginUrls().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
