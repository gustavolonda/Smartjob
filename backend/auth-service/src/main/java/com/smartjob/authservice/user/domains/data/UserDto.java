package com.smartjob.authservice.user.domains.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartjob.authservice.phone.domains.data.PhoneDto;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.smartjob.authservice.commons.api.domains.data.UtilApi.*;
import static java.lang.Boolean.TRUE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    @Pattern(regexp = EMAIL_PATTERN)
    private String email;
    @Pattern(regexp = PASSWORD_PATTERN)
    private String password;
    @JsonFormat(shape = STRING, pattern = FORMAT_DATE)
    private List<PhoneDto> phones;
    private String token;
    @JsonFormat(shape = STRING, pattern = FORMAT_DATE)
    private Date lastLogin;
    @JsonFormat(shape = STRING, pattern = FORMAT_DATE)
    private Date created;
    @JsonFormat(shape = STRING, pattern = FORMAT_DATE)
    private Date modified;
    private boolean active = TRUE;
}
