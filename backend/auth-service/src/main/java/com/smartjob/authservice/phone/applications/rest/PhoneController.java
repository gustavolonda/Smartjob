package com.smartjob.authservice.phone.applications.rest;
import com.smartjob.authservice.commons.api.domains.data.ResponseBase;
import com.smartjob.authservice.phone.domains.data.PhoneDto;
import com.smartjob.authservice.phone.domains.services.PhoneService;
import com.smartjob.authservice.phone.infraestructure.entities.PhoneEntity;
import com.smartjob.authservice.phone.infraestructure.mappers.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.smartjob.authservice.commons.api.domains.data.StatusResponse.OK;
import static com.smartjob.authservice.commons.api.domains.data.UtilApi.getMessage;
import static com.smartjob.authservice.phone.infraestructure.configs.Constants.PHONE_URL;


//@RestController
//@RequestMapping(path = PHONE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "*")
public class PhoneController {
    @Autowired
    PhoneService service;

    @Autowired
    PhoneMapper phoneMapper;
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok()
                .body(ResponseBase.builder()
                        .status(OK.getValue())
                        .message(getMessage("smartjob.api.response.ok"))
                        .result(phoneMapper.toPhoneDto(service.getAll()))
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PhoneDto phone ) {
        return ResponseEntity.ok()
                .body(ResponseBase.builder()
                        .status(OK.getValue())
                        .message(getMessage("smartjob.api.response.ok"))
                        .result(phoneMapper.toPhoneDto(service.save(phoneMapper.toPhoneEntity(phone))))
                        .build());
    }
}
