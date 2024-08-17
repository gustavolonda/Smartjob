package com.smartjob.authservice.user.infraestructure.entities;

import com.smartjob.authservice.commons.api.infraestructure.entities.BaseEntity;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity  extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "text")
    private String id;
    private String name;
    private String email;
    private String password;
    @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "userEntity",
                cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;
    private String token;
    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    private Date lastLogin;

}
