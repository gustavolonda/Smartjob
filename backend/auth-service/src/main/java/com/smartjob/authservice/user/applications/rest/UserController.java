package com.smartjob.authservice.user.applications.rest;

import com.smartjob.authservice.commons.api.domains.data.ResponseBase;
import com.smartjob.authservice.phone.domains.services.PhoneService;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.domains.services.UserService;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import com.smartjob.authservice.user.infraestructure.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.smartjob.authservice.commons.api.domains.data.StatusResponse.OK;
import static com.smartjob.authservice.commons.api.domains.data.UtilApi.getMessage;
import static com.smartjob.authservice.user.infraestructure.configs.Constants.USER_URL;

//@RestController
//@RequestMapping(path = USER_URL, produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    UserMapper userMapper;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok()
                .body(ResponseBase.builder()
                        .status(OK.getValue())
                        .message(getMessage("smartjob.api.response.ok"))
                        .result(userMapper.toUserDto(service.getAll()))
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDto user ) {
        return ResponseEntity.ok()
                .body(ResponseBase.builder()
                        .status(OK.getValue())
                        .message(getMessage("smartjob.api.response.ok"))
                        .result(userMapper.toUserDto(service.save(userMapper.toUserEntity(user))))
                        .build());
    }
}
