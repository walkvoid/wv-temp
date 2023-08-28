package com.walkvoid.temp.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.walkvoid.temp.support.json.MyLocalDateTimeDeserializer;
import com.walkvoid.temp.support.json.MyLocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author walkvoid
 * @version 1.0
 * @date 2023/8/27
 * @desc jackson的简单使用
 */
@Slf4j
public class JacksonAnnotationUseTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
    }

    //POJO
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String name;
        private Integer age;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty(value = "last_login_time")
        @JsonSerialize(using = MyLocalDateTimeSerializer.class)
        @JsonDeserialize(using = MyLocalDateTimeDeserializer.class)
        private LocalDateTime lastLoginTime;


    }

    @Test
    public void jsonFormatTest() throws JsonProcessingException {
        User walkvoid = new User("walkvoid", 18, LocalDateTime.now().minusDays(1));
        log.info("{}", objectMapper.writeValueAsString(walkvoid));
        //{"name":"walkvoid","age":18,"lastLoginTime":"2023-08-27 14:32:27"}
    }

    @Test
    public void jsonPropertyTest() throws JsonProcessingException {
        User walkvoid = new User("walkvoid", 18, LocalDateTime.now().minusDays(1));
        log.info("{}", objectMapper.writeValueAsString(walkvoid));
        //{"name":"walkvoid","age":18,"last_login_time":"2023-08-27 14:32:27"}
    }







}
