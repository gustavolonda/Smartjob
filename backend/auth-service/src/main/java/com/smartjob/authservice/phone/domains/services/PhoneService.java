package com.smartjob.authservice.phone.domains.services;

import com.smartjob.authservice.commons.api.domains.exception.BaseException;
import com.smartjob.authservice.commons.api.domains.services.EndPointServiceImpl;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import com.smartjob.authservice.phone.infraestructure.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneService extends EndPointServiceImpl<PhoneEntity, String> {
    public static final String TAG = PhoneService.class.getSimpleName();
    @Autowired
    private PhoneRepository repository;

    @Override
    public JpaRepository<PhoneEntity, String> getDao() {
        return this.repository;
    }

    @Override
    public PhoneEntity statusChangeDelete(PhoneEntity entity) {
        entity.setActive(Boolean.FALSE);
        return entity;
    }

    @Override
    public String nameModule() {
        return TAG;
    }

    @Override
    public BaseException validate(PhoneEntity entity) {
        return null;
    }
}
