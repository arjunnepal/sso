package com.custom.spring.sso.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    @NotBlank(message = "[name] is required.")
    private String name;
    List<String> permissionIds;
    private String url;
    private String parentId;
}
