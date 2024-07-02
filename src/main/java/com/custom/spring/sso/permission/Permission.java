package com.custom.spring.sso.permission;

import com.custom.spring.sso.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
}
