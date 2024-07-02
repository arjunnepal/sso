package com.custom.spring.sso.permission;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PermissionMapper {

    static BiFunction<PermissionDTO, String, Permission> toEntity = (dto, id) -> Permission.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .id(id)
            .build();

    static Function<Permission, PermissionResource> toResource = (permission) ->
            permission == null ? null : new PermissionResource(permission.getId(),
                    permission.getName(),
                    permission.getDescription());
}
