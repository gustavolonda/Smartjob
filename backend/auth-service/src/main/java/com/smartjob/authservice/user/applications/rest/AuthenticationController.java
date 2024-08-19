package com.smartjob.authservice.user.applications.rest;

import com.smartjob.authservice.commons.api.domains.data.ResponseBase;
import com.smartjob.authservice.commons.api.domains.exception.ErrorResponse;
import com.smartjob.authservice.commons.api.domains.exception.ResponseException;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.domains.services.UserService;
import com.smartjob.authservice.user.infraestructure.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

@Tag(name = "Auth", description = "Api de registro de usuairo")
@RequestMapping(AUTH_URL)
@RestController
public class AuthenticationController {
    @Autowired
    UserService service;
    @Autowired
    UserMapper userMapper;

    @Operation(
            summary = "Registar un usuario",
            description = "Se registra un usuario con sus numeros de telefono")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseBase.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = ResponseException.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = ResponseException.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ResponseException.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "503", content = { @Content(schema = @Schema(implementation = ResponseException.class), mediaType = "application/json") })
    })
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
