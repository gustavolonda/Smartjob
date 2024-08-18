package com.smartjob.authservice.commons.api.infraestructure.entities;
import java.util.Date;


import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder(toBuilder = true)
public abstract  class BaseEntity {
    @CreationTimestamp
    @Column(name = "created" , columnDefinition = "TIMESTAMP")
    private Date created;

    @UpdateTimestamp
    @Column(name = "modified" , columnDefinition = "TIMESTAMP")
    private Date modified;

    private boolean active = Boolean.TRUE;
}