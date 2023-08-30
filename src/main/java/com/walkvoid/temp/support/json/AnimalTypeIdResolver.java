package com.walkvoid.temp.support.json;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.walkvoid.temp.models.json.JsonSubTypeModel;

import java.io.IOException;

/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
public class AnimalTypeIdResolver extends TypeIdResolverBase {


    //需要 @JsonTypeInfo的include = JsonTypeInfo.As.PROPERTY,property = "type"才有效
    //jackson会追加一个fieldValue到json，field=type， value就是idFromValue()的返回值
    @Override
    public String idFromValue(Object value) {
        return "haha";
    }

    //同上
    @Override
    public String idFromValueAndType(Object value, Class<?> type) {
        return "xixi";
    }

    //根据你的标志性值 id， 返回你选择的class类型
    @Override
    public JavaType typeFromId(DatabindContext context, String id)  throws IOException {

        return SimpleType.constructUnsafe(JsonSubTypeModel.Cat.class);

    }


    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
