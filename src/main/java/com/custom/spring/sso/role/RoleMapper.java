package com.custom.spring.sso.role;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RoleMapper {

    static BiFunction<RoleDTO, String, Role> toEntity = (dto, id) -> Role.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .id(id)
            .build();

    static Function<Role, RoleResource> toResource = (role) ->
            role == null ? null : new RoleResource(role.getId(), role.getName(), role.getDescription());
}
