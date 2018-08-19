package com.cjwx.titan.engine.core.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.HashMap;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月18日 16:59
 */
public abstract class AbstractBox<T> extends HashMap<String, T> {

    public Integer getInteger(String key) {
        Object value = this.get(key);
        return value == null ? 0 : TypeUtils.castToInt(value);
    }

    public Boolean getBoolean(String key) {
        Object value = this.get(key);
        return value == null ? false : TypeUtils.castToBoolean(value);
    }

    public String getString(String key) {
        Object value = this.get(key);
        return value == null ? null : value.toString();
    }

    public JSONArray getJSONArray(String key) {
        Object value = this.get(key);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        } else {
            return value instanceof String ? (JSONArray) JSON.parse((String) value) : (JSONArray) JSON.toJSON(value);
        }
    }

}
