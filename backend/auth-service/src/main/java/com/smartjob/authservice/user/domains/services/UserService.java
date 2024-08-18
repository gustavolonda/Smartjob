package com.smartjob.authservice.user.domains.services;

import com.smartjob.authservice.commons.api.domains.data.BaseException;
import com.smartjob.authservice.commons.api.domains.services.EndPointServiceImpl;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import com.smartjob.authservice.user.infraestructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends EndPointServiceImpl<UserEntity, String> {
    public static final String TAG = UserService.class.getSimpleName();

    @Autowired
    private UserRepository repository;

    @Override
    public JpaRepository<UserEntity, String> getDao() {
        return this.repository;
    }

    @Override
    public UserEntity statusChangeDelete(UserEntity entity) {
        return null;
    }

    @Override
    public String nameModule() {
        return TAG;
    }

    @Override
    public BaseException validate(UserEntity entity) {
        return null;
    }
}
