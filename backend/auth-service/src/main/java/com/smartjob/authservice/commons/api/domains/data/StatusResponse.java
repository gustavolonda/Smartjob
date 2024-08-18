package com.smartjob.authservice.commons.api.domains.data;

import lombok.Getter;

@Getter
public enum StatusResponse {
    OK("Ok"), ERROR("Error");
    private String value;

    private StatusResponse(String value) {
        this.value = value;
    }
}