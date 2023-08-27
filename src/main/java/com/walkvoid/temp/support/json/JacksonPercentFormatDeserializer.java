package com.walkvoid.temp.support.json;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.walkvoid.temp.anno.JacksonPercentFormat;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/12
 * @desc
 */
public class JacksonPercentFormatDeserializer extends JsonDeserializer<Number> implements ContextualDeserializer {

    protected JacksonPercentFormat jacksonPercentFormat;

    protected Class<?> rawClass;

    public JacksonPercentFormatDeserializer() {
    }

    public JacksonPercentFormatDeserializer(JacksonPercentFormat jacksonPercentFormat, Class<?> rawClass) {
        this.jacksonPercentFormat = jacksonPercentFormat;
        this.rawClass = rawClass;
    }

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        int scale = this.jacksonPercentFormat.scale();
        if (StringUtils.hasText(p.getText())) {
            String replace = p.getText().replace("%", "");
            BigDecimal result = new BigDecimal(replace).divide(new BigDecimal(100), scale + 2, RoundingMode.HALF_UP);
            if (BigDecimal.class.equals(this.rawClass)) {
                return result;
            } else if (Double.class.equals(this.rawClass)) {
                return result.doubleValue();
            } else if (Float.class.equals(this.rawClass)) {
                return result.doubleValue();
            }
        }
        return null;
    }



    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JacksonPercentFormat annotation = property.getAnnotation(JacksonPercentFormat.class);
        List<? extends Class<? extends Number>> supportClasses = Arrays.asList(BigDecimal.class, Double.class, Float.class);
        if (annotation != null && supportClasses.contains(property.getType().getRawClass())) {
            return new JacksonPercentFormatDeserializer(annotation, property.getType().getRawClass());
        } else {
            return ctxt.findContextualValueDeserializer(property.getType(), property);
        }
    }
}
