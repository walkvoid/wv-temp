package com.walkvoid.temp.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
        @JsonProperty
        private LocalDate birthday;
        private Car car;

        //@JsonCreator
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
        User walkvoid = new User("walkvoid", 18, null, new Car("my baby cat", "benz"));

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

    @Test
    public void java8TimeTest1() throws IOException {
        User walkvoid = new User("walkvoid", 18, LocalDate.now().minusDays(3600L),new Car("my baby cat", "benz"));
        //使用自定义创建器 反序列化对象
        String userJson = objectMapper.writeValueAsString(walkvoid);
        log.info("{}",userJson);
        //{"name":"walkvoid","age":18,"birthday":[2013,10,19],"car":{"name":"my baby cat","brand":"benz"}}
    }

    @Test
    public void manualCreateJson() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JsonGenerator generator = objectMapper.getFactory().createGenerator(bos, JsonEncoding.UTF8);
        generator.writeStartObject();
        generator.writeStringField("name", "walkvoid");
        generator.writeNumberField("age", 18);
        generator.writeObjectField("birthday", LocalDate.now().minusDays(3600));
        generator.writeEndObject();
        generator.close();
        log.info("{}",new String(bos.toByteArray()));
        //{"name":"walkvoid","age":18,"birthday":[2013,10,19]}
    }

    @Test
    public void manualParseJson() throws IOException {
        String json = "{\"name\":\"walkvoid\",\"age\":18,\"birthday\":[2013,10,19]}";
        JsonParser jsonParser = objectMapper.createParser(json);

        JsonToken jsonToken1 = jsonParser.nextToken(); // {
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken2 = jsonParser.nextToken();// name
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken3 = jsonParser.nextToken(); //walkvoid
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken4 = jsonParser.nextToken(); // age
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken5 = jsonParser.nextToken(); // 18
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken6 = jsonParser.nextToken(); // birthday
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken7 = jsonParser.nextToken(); // [
        log.info("{}", jsonParser.getText());

        JsonToken jsonToken8 = jsonParser.nextToken(); // 2013
        log.info("{}", jsonParser.getText());

    }





}
