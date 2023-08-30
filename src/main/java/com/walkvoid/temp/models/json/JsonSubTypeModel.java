package com.walkvoid.temp.models.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.walkvoid.temp.support.json.AnimalTypeIdResolver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
public class JsonSubTypeModel {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
    @JsonTypeIdResolver(AnimalTypeIdResolver.class)
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
    public static class Cat extends Animal {
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
}
