package com.smartjob.authservice.user.domains.services;

import com.smartjob.authservice.commons.api.domains.exception.BaseException;
import com.smartjob.authservice.commons.api.domains.services.EndPointServiceImpl;
import com.smartjob.authservice.commons.api.domains.services.JwtService;
import com.smartjob.authservice.phone.domains.services.PhoneService;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import com.smartjob.authservice.user.infraestructure.repositories.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.smartjob.authservice.commons.api.domains.data.UtilApi.getMessage;
import static com.smartjob.authservice.user.domains.services.UserValidate.userFieldValidationError;

@Log4j2
@Service
public class UserService extends EndPointServiceImpl<UserEntity, String>  implements UserDetailsService {
    public static final String TAG = UserService.class.getSimpleName();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repository;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PhoneService phoneService;

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
        return userFieldValidationError(entity);
    }
    @SneakyThrows
    public UserEntity register(UserEntity userEntity) {
        try {
            if (repository.findByEmail(userEntity.getEmail()).orElse(null) != null) {
                BaseException baseException =   BaseException.builder()
                                                        .message(getMessage("smartjob.api.auth.use-exists.error"))
                                                        .module(nameModule())
                                                        .exception(null)
                                                        .build();
                throw  baseException;
            }
            List<PhoneEntity> phoneEntityList = userEntity.getPhones();
            String password = userEntity.getPassword();
            String passwordEncoded = !password.isEmpty() ? passwordEncoder.encode(password):"";
            userEntity.setPassword(passwordEncoded);
            userEntity = this.save(userEntity);
            for(PhoneEntity phoneEntity : phoneEntityList){
                phoneEntity.setUser(userEntity);
            }
            phoneService.saveAll(phoneEntityList);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userEntity.getEmail(),
                            password
                    )
            );
            String jwtToken = jwtService.generateToken(userEntity);
            userEntity.setToken(jwtToken);
            return this.save(userEntity);
        }catch (Exception e) {
            log.info("smartjob.api.auth.register.error",e);
            throw e;
        }
    }

    public UserEntity login(UserEntity userEntity) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEntity.getEmail(),
                        userEntity.getPassword()
                )
        );
        UserEntity user = repository.findByEmail(userEntity.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        this.update(user);
        return user ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow();
    }
}
