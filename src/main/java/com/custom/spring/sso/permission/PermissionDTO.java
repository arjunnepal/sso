package com.custom.spring.sso.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {
    @NotBlank(message = "[name] is required.")
    private String name;

    @NotBlank(message = "[description] is required.")
    private String description;
}
