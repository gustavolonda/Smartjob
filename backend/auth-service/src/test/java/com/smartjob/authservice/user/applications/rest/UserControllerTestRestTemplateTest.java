package com.smartjob.authservice.user.applications.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.smartjob.authservice.AuthServiceApplication;
import com.smartjob.authservice.commons.api.infraestructure.configs.JwtAuthFilter;
import com.smartjob.authservice.commons.api.infraestructure.configs.SecurityConfiguration;
import com.smartjob.authservice.phone.domains.data.PhoneDto;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.domains.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = { "com.smartjob.authservice.*"})
@EnableJpaRepositories(basePackages = {"com.smartjob.authservice.*"})
@EntityScan(basePackages = { "com.smartjob.authservice.*"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTestRestTemplateTest {
    @Autowired
    private TestRestTemplate client;

    @Autowired
    private MockMvc mvc;
    @LocalServerPort
    private int port = 8095;
   // @Test
    @Order(1)
    void whenRegisterUser_thenReturnOK() throws JsonProcessingException {
        PhoneDto phone = PhoneDto.builder()
                                .number("0992378567")
                                .cityCode("23")
                                .countryCode("593")
                                .build();
        List<PhoneDto> phoneDtoList = new ArrayList();
        phoneDtoList.add(phone);
        UserDto userDto = UserDto.builder()
                                .name("Juan Lopez")
                                .email("juanlopez@gmail.com")
                                .password("sdfdsfdsfewfre")
                                .phones(phoneDtoList)
                                .build();

        ResponseEntity<String> response = client.
                postForEntity(uriCreate("/api/v1/auth/register"), userDto, String.class);

        String json = response.getBody();
        System.out.println(json);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

    }

    //@Test
    @Order(2)
    void whenRegisterUserWithEmailNotValid_thenReturnBadRequest() throws JsonProcessingException {
        PhoneDto phone = PhoneDto.builder()
                .number("0992378567")
                .cityCode("23")
                .countryCode("593")
                .build();
        List<PhoneDto> phoneDtoList = new ArrayList();
        phoneDtoList.add(phone);
        UserDto userDto = UserDto.builder()
                .name("Juan Lopez")
                .email("juanlopez@gmail")
                .password("sdfdsfdsfewfre")
                .phones(phoneDtoList)
                .build();

        ResponseEntity<String> response = client.
                postForEntity(uriCreate("/api/v1/auth/register"), userDto, String.class);

        String json = response.getBody();
        System.out.println(json);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

    }
   // @Test
    @Order(3)
    void whenRegisterUserWithPasswordEmpty_thenReturnBadRequest() throws JsonProcessingException {
        PhoneDto phone = PhoneDto.builder()
                .number("0992378567")
                .cityCode("23")
                .countryCode("593")
                .build();
        List<PhoneDto> phoneDtoList = new ArrayList();
        phoneDtoList.add(phone);
        UserDto userDto = UserDto.builder()
                .name("Juan Lopez")
                .email("juanlopez@gmail.com")
                .password("")
                .phones(phoneDtoList)
                .build();

        ResponseEntity<String> response = client.
                postForEntity(uriCreate("/api/v1/auth/register"), userDto, String.class);

        String json = response.getBody();
        System.out.println(json);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

    }
    private String uriCreate(String uri) {
        return "http://localhost:" + port + uri;
    }
}
