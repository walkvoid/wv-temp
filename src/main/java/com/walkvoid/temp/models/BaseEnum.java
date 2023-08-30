package com.walkvoid.temp.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.walkvoid.temp.support.json.BaseEnumDeserializer;
import com.walkvoid.temp.support.json.BaseEnumSerializer;


/**
 * @author jiangjunqing
 * @version v1.0.0
 * @date 2023/8/30
 * @desc
 */
@JsonSerialize(using = BaseEnumSerializer.class)
@JsonDeserialize(using = BaseEnumDeserializer.class)
public interface BaseEnum {



}
