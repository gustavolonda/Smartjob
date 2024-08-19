package com.smartjob.authservice.user.applications.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartjob.authservice.commons.api.domains.data.UtilApi;
import com.smartjob.authservice.commons.api.domains.services.JwtService;
import com.smartjob.authservice.phone.domains.data.PhoneDto;
import com.smartjob.authservice.phone.domains.services.PhoneService;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.domains.services.UserService;
import com.smartjob.authservice.user.infraestructure.configs.MyTestConfig;
import com.smartjob.authservice.user.infraestructure.entities.UserEntity;
import com.smartjob.authservice.user.infraestructure.mappers.UserMapper;
import com.smartjob.authservice.user.infraestructure.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static com.smartjob.authservice.user.domains.data.UnitTestingData.responseEmailNotVaild;
import static com.smartjob.authservice.user.domains.data.UnitTestingData.responseOk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthenticationController.class)
@ContextConfiguration(classes = {MyTestConfig.class})
public class AuthenticationControllerTest {
    private MockMvc mvc;

    @Autowired
    private AuthenticationController controller;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PhoneService phoneService;
    @MockBean
    private UtilApi utilApi;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private JwtService jwtService;

    ObjectMapper objectMapper;

    UserDto userDto;
    UserDto userEmailNotValidDto;

    @BeforeEach
    void setUp() {
        mvc= MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        PhoneDto phoneDto = PhoneDto.builder()
                                    .number("0992378567")
                                    .cityCode("23")
                                    .countryCode("593")
                                    .build();
        List<PhoneDto> phoneDtoList = new ArrayList();
        phoneDtoList.add(phoneDto);
        userDto = UserDto.builder()
                        .name("Juan Lopez")
                        .email("juanlopez@gmail")
                 .password("123456")
                        .phones(phoneDtoList)
                        .build();
        userEmailNotValidDto = userDto;
        userEmailNotValidDto.setEmail("");
    }

    @Test
    void whenRegisterUser_thenReturnOK() throws Exception {
        // Given
        when(userMapper.toUserDto(userService.register(userMapper.toUserEntity(userDto)))).thenReturn(responseOk().orElseThrow());
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message","The process ended successfully");
        response.put("result", responseOk());

        System.out.println(objectMapper.writeValueAsString(response));

        // When
        mvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                // Then
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result.name").value("Juan Lopez"));
    }

    @Test
    void whenRegisterUserWithEmailNotValid_thenReturnBadRequest() throws Exception {
        // Given
        String exceptionParam = "bad_arguments";
        Map<String, Object> response = new HashMap<>();
        response.put("message","The email field is not valid");

        System.out.println(objectMapper.writeValueAsString(response));
        when(userService.validate(userMapper.toUserEntity(userEmailNotValidDto)))
                .thenAnswer(i -> {throw responseEmailNotVaild();});
        // When
        mvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(response)))
                // Then
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("bad arguments", result.getResolvedException().getMessage()));

    }

}
