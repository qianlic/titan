package com.cjwx.titan.engine.core.model;

import com.cjwx.titan.engine.util.ObjectUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 容器
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public final class Model extends AbstractBox<Object> {

    private static String KEY_ID = "id";
    private static String KEY_START = "start";
    private static String KEY_SIZE = "size";
    private static String KEY_PARSMS = "params";

    private long requestStartTime;

    public Model() {
        this.requestStartTime = System.currentTimeMillis();
    }

    public Integer getId() {
        return this.getInteger(KEY_ID);
    }

    public Integer getStart() {
        return this.getInteger(KEY_START);
    }

    public Integer getSize() {
        return this.getInteger(KEY_SIZE);
    }

    public Map<String, Object> getParams() {
        Object o = this.get(KEY_PARSMS);
        if (o instanceof Map) {
            return (HashMap<String, Object>) o;
        }
        return ObjectUtils.objectToHashMap(o);
    }

}
