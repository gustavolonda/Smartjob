package com.smartjob.authservice.user.infraestructure.entities;

import com.smartjob.authservice.commons.api.infraestructure.entities.BaseEntity;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity  extends BaseEntity implements UserDetails {
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
                mappedBy = "user",
                cascade = CascadeType.MERGE)
    private List<PhoneEntity> phones;
    private String token;
    @Column(name = "last_login", columnDefinition = "TIMESTAMP")
    private Date lastLogin;

    @PrePersist
    public void lastLoginWhenSaveInstance() {
        this.lastLogin = new Date();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
