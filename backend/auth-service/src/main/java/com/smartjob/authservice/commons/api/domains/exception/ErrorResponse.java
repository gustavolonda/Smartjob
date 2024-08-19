package com.smartjob.authservice.commons.api.domains.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {
    private String code ;
    private Object errors ;

}