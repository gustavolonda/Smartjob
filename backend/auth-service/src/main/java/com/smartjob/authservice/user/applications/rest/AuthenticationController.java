package com.smartjob.authservice.user.applications.rest;

import com.smartjob.authservice.commons.api.domains.data.ResponseBase;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.domains.services.UserService;
import com.smartjob.authservice.user.infraestructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.smartjob.authservice.commons.api.domains.data.StatusResponse.OK;
import static com.smartjob.authservice.commons.api.domains.data.UtilApi.getMessage;
import static com.smartjob.authservice.user.infraestructure.configs.Constants.AUTH_URL;
import static com.smartjob.authservice.user.infraestructure.configs.Constants.REGISTER_URL;

@RequestMapping(AUTH_URL)
@RestController
public class AuthenticationController {
    @Autowired
    UserService service;
    @Autowired
    UserMapper userMapper;
    @PostMapping(REGISTER_URL)
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok()
                .body(ResponseBase.builder()
                        .status(OK.getValue())
                        .message(getMessage("smartjob.api.response.ok"))
                        .result(userMapper.toUserDto(
                                        service.register(userMapper.toUserEntity(userDto))
                                        )
                                )
                        .build());
    }
}
