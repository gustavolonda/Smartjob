package com.smartjob.authservice.phone.infraestructure.repositories;

import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
}
