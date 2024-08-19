package com.smartjob.authservice.user.domains.services;

import com.smartjob.authservice.commons.api.domains.exception.BaseException;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;


import static com.smartjob.authservice.commons.api.domains.data.UtilApi.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;



public class UserValidate {
    public static final String TAG = UserValidate.class.getSimpleName();
    public static BaseException userFieldValidationError(UserEntity userEntity){
        boolean isError = FALSE;
        String message= new String();
        if(!emailValidate(userEntity.getEmail())){
            isError = TRUE;
            message = getMessage("smartjob.api.auth.email.error");
        }

        if(!passwordValidate(userEntity.getPassword())){
            isError = TRUE;
            message = getMessage("smartjob.api.auth.password.error");
        }

        if(!nameValidate(userEntity.getName())){
            isError = TRUE;
            message = getMessage("smartjob.api.auth.name.error");
        }

        if (isError){
            return BaseException.builder()
                    .message(message)
                    .module(TAG)
                    .exception(null)
                    .build();
        }


        return null;
    }
    public static boolean emailValidate(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches();
    }

    public static boolean passwordValidate(String password) {
        return VALID_NON_EMPTY.matcher(password).matches();
    }

    public static boolean nameValidate(String name) {
        return VALID_NON_EMPTY.matcher(name).matches();
    }

}
