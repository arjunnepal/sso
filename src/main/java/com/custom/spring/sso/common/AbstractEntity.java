package com.custom.spring.sso.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Persistable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    @Setter
    private String id;

    @JsonIgnore
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @CreatedDate
    private Date created;

    @JsonIgnore
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @LastModifiedDate
    private Date lastModified;

    @Version
    private Long version;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @PrePersist
    public void onPersist() {
        this.created = new Date();
        this.lastModified = new Date();

    }

    @PreUpdate
    public void onUpdate() {
        this.lastModified = new Date();
    }

}
