package com.custom.spring.sso.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UserMapper {
    static Function<User, CustomUserDetails> mapToCustomUserDetails = ud -> CustomUserDetails.builder()
            .id(ud.getId())
            .username(ud.getUsername())
            .password(ud.getPassword())
            .accountNonExpired(ud.isAccountNonExpired())
            .accountNonLocked(ud.isAccountNonLocked())
            .credentialsNonExpired(ud.isCredentialsNonExpired())
            .enabled(ud.isEnabled())
            .createdAt(LocalDateTime.ofInstant(ud.getCreated().toInstant(), ZoneId.systemDefault()))
            .firstName(ud.getFirstName())
            .lastName(ud.getLastName())
            .middleName(ud.getMiddleName())
            .build();

    static Function<User, UserResource> toResource = u -> UserResource
            .builder()
            .username(u.getUsername())
            .accountNonExpired(u.isAccountNonExpired())
            .accountNonLocked(u.isAccountNonLocked())
            .credentialsNonExpired(u.isCredentialsNonExpired())
            .enabled(u.isEnabled())
            .id(u.getId())
            .build();

    static BiFunction<UserDTO, String, User> toEntity = (u, id) -> User.builder()
            .username(u.getUsername())
            .accountNonExpired(u.isAccountNonExpired())
            .accountNonLocked(u.isAccountNonLocked())
            .credentialsNonExpired(u.isCredentialsNonExpired())
            .enabled(u.isEnabled())
            .id(id)
            .build();

}
