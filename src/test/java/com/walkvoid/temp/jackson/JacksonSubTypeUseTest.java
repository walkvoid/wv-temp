package com.walkvoid.temp.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.walkvoid.temp.support.json.MyLocalDateTimeDeserializer;
import com.walkvoid.temp.support.json.MyLocalDateTimeSerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author walkvoid
 * @version 1.0
 * @date 2023/8/27
 * @desc jackson的简单使用
 */
@Slf4j
public class JacksonSubTypeUseTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM,property = "color", visible = false)
    //@ObjectIdResolver()
    @JsonSubTypes({@JsonSubTypes.Type(value= Cat.class,name = "yellow")})
    public abstract static class Animal {
        private String name;
        //C=猫, EL=大象
        private String type;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    public static class Cat extends Animal{
        private String color;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    public static class Elephant extends Animal{

        private Integer weight;
    }

    @Test
    public void subTypeTest() throws JsonProcessingException {
        Animal animal = Cat.builder().name("cat").color("yellow").build();
        log.info("{}", objectMapper.writeValueAsString(animal));
        //{"name":"cat","color":"yellow"}

        String json = "{\"name\":\"cat\",\"color\":\"yellow\"}";
        Animal animal1 = objectMapper.readValue(json, Animal.class);
        // animal1 is
    }









}
