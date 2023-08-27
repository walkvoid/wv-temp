package com.walkvoid.temp.support.json;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.walkvoid.temp.anno.JacksonMoneyFormat;
import com.walkvoid.temp.anno.MoneyUnit;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/12
 * @desc
 */
public class JacksonMoneyFormatSerializer extends JsonSerializer<Number> implements ContextualSerializer {

    protected JacksonMoneyFormat jacksonMoneyFormat;

    protected Class<?> rawClass;

    public JacksonMoneyFormatSerializer() {}

    public JacksonMoneyFormatSerializer(JacksonMoneyFormat jacksonMoneyFormat,  Class<?> rawClass) {
        this.jacksonMoneyFormat = jacksonMoneyFormat;
        this.rawClass = rawClass;
    }


    @Override
    public void serialize(Number value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value == null) {
            return;
        }
        int scale = this.jacksonMoneyFormat.scale();
        MoneyUnit unit = this.jacksonMoneyFormat.unit();
        int divisor = unit.divisor();
        if (divisor <= 0) {
            divisor = unit.type().getDivisor();
        }
        BigDecimal divide = BigDecimal.valueOf(Math.pow(10, divisor));
        BigDecimal result = null;
        if (value instanceof BigDecimal) {
            result = ((BigDecimal) value).divide(divide, scale, RoundingMode.HALF_UP);
        } else if (value instanceof Float){
            result = BigDecimal.valueOf((Float) value).divide(divide, scale, RoundingMode.HALF_UP);
        }else if (value instanceof Double){
            result = BigDecimal.valueOf((Double) value).divide(divide, scale, RoundingMode.HALF_UP);
        }else if (value instanceof Long){
            result = BigDecimal.valueOf((Long) value).divide(divide, scale, RoundingMode.HALF_UP);
        }else if (value instanceof Integer){
            result = BigDecimal.valueOf((Integer) value).divide(divide, scale, RoundingMode.HALF_UP);
        }
        if (result != null && this.jacksonMoneyFormat.stripTrailingZeros()) {
            result = result.stripTrailingZeros();
        }
        String text = null;
        if (result != null && this.jacksonMoneyFormat.withThousandSeparator()) {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            text = numberInstance.format(result);
        }

        if (result != null && text == null) {
            text = result.toString();
        }

        if (result != null && StringUtils.hasText(this.jacksonMoneyFormat.withSymbol())) {
            text = this.jacksonMoneyFormat.withSymbol() + text;
        }
        if (StringUtils.hasText(text)) {
            jsonGenerator.writeString(text);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JacksonMoneyFormat annotation = beanProperty.getAnnotation(JacksonMoneyFormat.class);
        List<? extends Class<? extends Number>> supportClasses = Arrays.asList(BigDecimal.class, Double.class, Float.class, Long.class, Integer.class);
        if (annotation != null && supportClasses.contains(beanProperty.getType().getRawClass())) {
            return new JacksonMoneyFormatSerializer(annotation, beanProperty.getType().getRawClass());
        } else {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
    }
}
