package com.walkvoid.temp.support.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.walkvoid.temp.anno.JacksonMoneyFormat;
import com.walkvoid.temp.anno.MoneyUnit;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/7/12
 * @desc
 */
public class JacksonMoneyFormatDeserializer extends JsonDeserializer<Number>  implements ContextualDeserializer {

    protected JacksonMoneyFormat jacksonMoneyFormat;

    protected Class<?> rawClass;

    public JacksonMoneyFormatDeserializer() {}

    public JacksonMoneyFormatDeserializer(JacksonMoneyFormat jacksonMoneyFormat,  Class<?> rawClass) {
        this.jacksonMoneyFormat = jacksonMoneyFormat;
        this.rawClass = rawClass;
    }

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String text = p.getText();
        if (!StringUtils.hasText(text)) {
            return null;
        }
        if (StringUtils.hasText(this.jacksonMoneyFormat.withSymbol())) {
            text = text.replace(this.jacksonMoneyFormat.withSymbol(), "");
        }
        if (this.jacksonMoneyFormat.withThousandSeparator()) {
            text = text.replace(",", "");
        }

        MoneyUnit unit = this.jacksonMoneyFormat.unit();
        int divisor = unit.divisor();
        if (divisor <= 0) {
            divisor = unit.type().getDivisor();
        }
        BigDecimal multiply = BigDecimal.valueOf(Math.pow(10, divisor));
        BigDecimal result = new BigDecimal(text).multiply(multiply);
        if (BigDecimal.class.equals(this.rawClass)) {
            return result;
        } else if (Double.class.equals(this.rawClass)) {
            return result.doubleValue();
        } else if (Float.class.equals(this.rawClass)) {
            return Float.parseFloat(result.toString());
        } else if (Long.class.equals(this.rawClass)) {
            return result.longValue();
        } else if (Integer.class.equals(this.rawClass)) {
            return result.intValue();
        }
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JacksonMoneyFormat annotation = property.getAnnotation(JacksonMoneyFormat.class);
        List<? extends Class<? extends Number>> supportClasses = Arrays.asList(BigDecimal.class, Double.class, Float.class);
        if (annotation != null && supportClasses.contains(property.getType().getRawClass())) {
            return new JacksonMoneyFormatDeserializer(annotation, property.getType().getRawClass());
        } else {
            return ctxt.findContextualValueDeserializer(property.getType(), property);
        }
    }
}
