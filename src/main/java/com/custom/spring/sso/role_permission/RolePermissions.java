package com.custom.spring.sso.role_permission;


import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompositeKey.class)
@Entity
@Table(name = "role_permission")
public class RolePermissions {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Permission permission;

}
