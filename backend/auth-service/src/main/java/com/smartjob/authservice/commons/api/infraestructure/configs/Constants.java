package com.smartjob.authservice.commons.api.infraestructure.configs;

public class Constants {
    public static String SEPARATE                = "-";
    public static String SCOPE_READ      = "SCOPE_read:client_credentials";
    public static String SCOPE_CREATE    = "SCOPE_create:client_credentials";
    public static String SWAGGER_URL             = "/swagger-ui/**";
    public static String API_DOC_SWAGGER_URL     = "/v3/api-docs";
    public static String API_DOC_SWAGGER_SUB_URL = API_DOC_SWAGGER_URL + "/**";
    public static String SMARTJOB_REST_API = "/api/v1";
    public static String SMARTJOB_REST_API_AUTH = SMARTJOB_REST_API+"/auth";
    public static String SMARTJOB_REST_API_REGISTER = SMARTJOB_REST_API_AUTH+"/register";
    public static String SMARTJOB_REST_API_LOGIN = SMARTJOB_REST_API_AUTH+"/login";
}
