package com.custom.spring.sso.user;

import com.custom.spring.sso.common.AbstractRepository;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User> {
    Optional<User> findByUsername(String username);
}
