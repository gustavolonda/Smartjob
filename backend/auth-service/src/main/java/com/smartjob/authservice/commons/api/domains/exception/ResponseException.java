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
public class ResponseException {
    private String message;

    public static ResponseException responseExceptionCreate( Exception exception) {

        return ResponseException.builder()
                .message(exception.getMessage())
                .build();
    }

    public static ResponseException responseExceptionCreate( String message) {

        return ResponseException.builder()
                .message(message)
                .build();
    }

}
