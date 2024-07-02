package com.custom.spring.sso.menu;

import com.custom.spring.sso.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;

    private String url;

    @ManyToOne
    private Menu parent;
}
