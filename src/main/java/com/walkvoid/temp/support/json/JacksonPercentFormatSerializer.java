package com.walkvoid.temp.support.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.walkvoid.temp.anno.JacksonPercentFormat;
import lombok.extern.slf4j.Slf4j;
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
 * @desc https://www.jianshu.com/p/b47d03518044
 */
@Slf4j
public class JacksonPercentFormatSerializer extends JsonSerializer<Number> implements ContextualSerializer {

    protected JacksonPercentFormat jacksonPercentFormat;

    protected Class<?> rawClass;

    public JacksonPercentFormatSerializer() {

    }

    public JacksonPercentFormatSerializer(JacksonPercentFormat jacksonPercentFormat,  Class<?> rawClass) {
        this.jacksonPercentFormat = jacksonPercentFormat;
        this.rawClass = rawClass;
    }

    @Override
    public void serialize(Number value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        int scale = this.jacksonPercentFormat.scale();
        boolean withSymbol = this.jacksonPercentFormat.withSymbol();
        BigDecimal multiply = new BigDecimal(100);
        String str = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                BigDecimal divide = ((BigDecimal) value).multiply(multiply).setScale(scale, RoundingMode.HALF_UP);
                str =divide.toString();
            } else if (value instanceof Float){
                BigDecimal divide = BigDecimal.valueOf((Float) value).multiply(multiply).setScale(scale, RoundingMode.HALF_UP);
                str =divide.toString();
            }else if (value instanceof Double){
                BigDecimal divide = BigDecimal.valueOf((Double) value).multiply(multiply).setScale(scale, RoundingMode.HALF_UP);
                str =divide.toString();
            }else if (value instanceof Long){
                BigDecimal divide = BigDecimal.valueOf((Long) value).multiply(multiply).setScale(scale, RoundingMode.HALF_UP);
                str =divide.toString();
            }else if (value instanceof Integer){
                BigDecimal divide = BigDecimal.valueOf((Integer) value).multiply(multiply).setScale(scale, RoundingMode.HALF_UP);
                str =divide.toString();
            }
            if (withSymbol && StringUtils.hasText(str)) {
                str = str + "%";
            }
            gen.writeString(str);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        JacksonPercentFormat annotation = property.getAnnotation(JacksonPercentFormat.class);
        List<? extends Class<? extends Number>> supportClasses = Arrays.asList(BigDecimal.class, Double.class, Float.class);
        if (annotation != null && supportClasses.contains(property.getType().getRawClass())) {
            return new JacksonPercentFormatSerializer(annotation, property.getType().getRawClass());
        } else {
            return prov.findValueSerializer(property.getType(), property);
        }
    }
}
