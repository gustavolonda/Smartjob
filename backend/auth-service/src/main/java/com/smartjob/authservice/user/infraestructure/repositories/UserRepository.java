package com.smartjob.authservice.user.infraestructure.repositories;

import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
