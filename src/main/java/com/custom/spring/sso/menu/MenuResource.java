package com.custom.spring.sso.menu;

import com.custom.spring.sso.permission.PermissionResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MenuResource {
    private String id;
    private String name;
    private List<PermissionResource> permissionResources;
    private String url;
    private MenuResource parent;

    public MenuResource(String id, String name, String url, MenuResource parent) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.parent = parent;
    }
}
