package com.smartjob.authservice.phone.infraestructure.entities;
import com.smartjob.authservice.commons.api.infraestructure.entities.BaseEntity;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class  PhoneEntity extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",
                    strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "text")
    private String id;
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable=false)
    private UserEntity user;

}
