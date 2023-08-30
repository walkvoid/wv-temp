package com.walkvoid.temp.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.walkvoid.temp.models.BaseEnum;
import com.walkvoid.temp.models.UserStatusEnum;
import com.walkvoid.temp.models.json.JsonSubTypeModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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

    @Test
    public void subTypeTest() throws JsonProcessingException {
        JsonSubTypeModel.Animal animal = JsonSubTypeModel.Cat.builder().name("cat").color("yellow").build();
//        JsonSubTypeModel.Animal animal = JsonSubTypeModel.Cat.builder().name("tom").type("C").build();
        log.info("{}", objectMapper.writeValueAsString(animal));
        //{"name":"cat","type":"C","color":"yellow"}

//        String json = "{\"name\":\"cat\",\"type\":\"C\",\"color\":\"yellow\"}";
//        JsonSubTypeModel.Animal animal1 = objectMapper.readValue(json, JsonSubTypeModel.Animal.class);
//        log.info("{}", animal1.getClass().getName());



    }









}
