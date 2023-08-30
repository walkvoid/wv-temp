package com.walkvoid.temp.support.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
public class BaseEnumSerializer extends JsonSerializer<Enum> implements ContextualSerializer {



    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return null;
    }
}
