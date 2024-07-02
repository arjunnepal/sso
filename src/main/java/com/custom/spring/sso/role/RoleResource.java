package com.custom.spring.sso.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResource {
    private String id;
    private String name;
    private String description;
}
