package com.smartjob.authservice.user.applications.rest;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartjob.authservice.commons.api.domains.services.JwtService;
import com.smartjob.authservice.commons.api.infraestructure.configs.JwtAuthFilter;
import com.smartjob.authservice.commons.api.infraestructure.configs.SecurityConfiguration;
import com.smartjob.authservice.phone.domains.data.PhoneDto;
import com.smartjob.authservice.user.domains.data.UserDto;
import com.smartjob.authservice.user.domains.services.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTestRestTemplateTest {
   /* @Autowired
    private TestRestTemplate client;
    @MockBean
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @LocalServerPort
    private int port = 8095;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private SecurityConfiguration securityConfiguration;
    @MockBean
    private JwtAuthFilter jwtAuthFilter;
    @MockBean
    private AuthenticationController authenticationController;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private WebSecurityConfiguration webSecurityConfiguration;
    @MockBean
    private SecurityFilterChain securityFilterChain;
    @MockBean
    private UserService userService;
    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //This
        Arrays.stream(webApplicationContext.getBeanDefinitionNames())
                .map(name -> webApplicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);

    }
  //@Test
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
    }*/
}
