package com.custom.spring.sso.user_role;

import com.custom.spring.sso.role.Role;
import com.custom.spring.sso.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompositeKey.class)
@Table(name = "user_role")
@Entity
public class UserRole {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;

}
