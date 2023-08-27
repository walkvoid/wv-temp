package com.walkvoid.temp.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author walkvoid
 * @version 1.0
 * @date 2023/8/27
 * @desc jackson的简单使用
 */
@Slf4j
public class JacksonSimpleUseTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    //POJO
    @Setter
    //@Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String name;
        private Integer age;
        private Car car;

        @JsonCreator
        public User(@JsonProperty(value = "name", index = 0) String name,
                    @JsonProperty("age")Integer age,
                    @JsonProperty(value = "car.name", index = 2)String benzCarName) {
            this.name = name;
            this.age = age;
            this.car = new Car(benzCarName, "benz");
        }
    }

    //POJO
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Car {
        private String name;
        private String brand;
    }

    @Test
    public void writeTest1() throws IOException {
        User walkvoid = new User("walkvoid", 18, new Car("my baby cat", "benz"));

        //result is a string variable
        String walkvoid1 = objectMapper.writeValueAsString(walkvoid);
        log.info("walkvoid1:{}", walkvoid1);
        // {"name":"walkvoid","age":18,"car":{"name":"my baby cat","brand":"benz"}}


        //wirite json to file
        //objectMapper.writeValue(new File("/path/destination-fileName.txt"), walkvoid);
    }

    @Test
    public void readTest1() throws IOException {
        String walkvoidJson = "{\"name\":\"walkvoid\",\"age\":18,\"car\":{\"name\":\"my baby cat\",\"brand\":\"benz\"}}";

        //json to java bean
        User user = objectMapper.readValue(walkvoidJson, User.class);

        //json to list
        String listJson = "[\"A\", \"B\", \"C\"]";
        List list = objectMapper.readValue(listJson, List.class);

        //java bean's json to map
        Map<String, Object> map = objectMapper.readValue(walkvoidJson, Map.class);
        //map instanceof LinkedHashMap, map.get("car") also instanceof LinkedHashMap


        //json to tree
        JsonNode jsonNode = objectMapper.readTree(walkvoidJson);
        //jsonNode instanceof ObjectNode, jsonNode is root node
        JsonNode brandNode = jsonNode.at("/car/brand");
        String s = brandNode.asText();
        //s == "benz"
    }


    @Test
    public void jsonCreatorTest1() throws IOException {
        String walkvoidJson = "{\"name\":\"walkvoid\",\"age\":18,\"car\":{\"name\":\"my baby cat\",\"brand\":\"benz\"}}";
        //使用自定义创建器 反序列化对象
        User user = objectMapper.readValue(walkvoidJson, User.class);
    }





}
