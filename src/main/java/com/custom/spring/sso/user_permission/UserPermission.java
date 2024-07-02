package com.custom.spring.sso.user_permission;


import com.custom.spring.sso.permission.Permission;
import com.custom.spring.sso.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompositeKey.class)
@Entity
@Table(name = "user_permission")
public class UserPermission {

    @Id
    @OneToOne
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Permission permission;

}
