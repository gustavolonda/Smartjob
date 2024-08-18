package com.smartjob.authservice.phone.domains.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private String id;
    private String number;
    private String cityCode;
    private String countryCode;
    private UserDto user;
}
