package com.walkvoid.temp.support.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;


import java.io.IOException;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
public class BaseEnumDeserializer extends JsonDeserializer<Enum<?>> implements ContextualDeserializer {
    public static String BASE_ENUM_NAME = "";

    protected Class<?> rawClass;

    public BaseEnumDeserializer() {
    }

    public BaseEnumDeserializer(Class<?> rawClass) {
        this.rawClass = rawClass;
    }

    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        //1.判断当前类是不是BaseEnum的子类
        Class<?> rawClass = ctxt.getContextualType().getRawClass();
        //2.判断当前环境是不是spring容器的环境，如果是尝试从spring的环境变量中获取枚举序列化配置
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Class<?> rawClass = property.getType().getRawClass();
        if (rawClass != null) {
            return new BaseEnumDeserializer(rawClass);
        } else {
            return ctxt.findContextualValueDeserializer(property.getType(), property);
        }

    }
}
