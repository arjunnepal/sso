package com.custom.spring.sso.permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PermissionResource {
    private String id;
    private String name;
    private String description;
}
