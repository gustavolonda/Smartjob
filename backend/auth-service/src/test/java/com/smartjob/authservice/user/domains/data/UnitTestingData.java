package com.smartjob.authservice.user.domains.data;

import com.smartjob.authservice.commons.api.domains.exception.ResponseException;
import com.smartjob.authservice.phone.domains.data.PhoneDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UnitTestingData {
    public static Optional<UserDto> responseOk(){
        PhoneDto phoneDto = PhoneDto.builder()
                                    .id("f01fd786-5cff-414d-b514-c8b5cadc4bae")
                                    .number("0992378567")
                                    .cityCode("23")
                                    .countryCode("593")
                                    .build();
        List<PhoneDto> phoneDtoList = new ArrayList();
        phoneDtoList.add(phoneDto);
        return Optional.of(UserDto.builder()
                        .id("c003311a-819a-4c05-97f0-4f65a723acfe")
                        .name("Juan Lopez")
                        .email("juanlopez@gmail")
                        .password("$2a$10$xp.OqMaVgkAQpvr/ATqxSuGzi63tZs/CDIlFztaGn4isDzOhCaPlm")
                        .phones(phoneDtoList)
                        .build());
    }

    public static ResponseException responseEmailNotVaild(){
        return ResponseException.builder().message("The email field is not valid")
                .build();
    }
}
