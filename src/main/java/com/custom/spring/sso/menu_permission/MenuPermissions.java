package com.custom.spring.sso.menu_permission;


import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.menu.Menu;
import com.custom.spring.sso.menu_permission.CompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompositeKey.class)
@Entity
@Table(name = "menu_permission")
public class MenuPermissions {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Menu menu;
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Permission permission;

}
