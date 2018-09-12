package com.cjwx.titan.engine.util;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 对象操作工具类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class ObjectUtils {

    /**
     * 判断是否为空对象
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof Collection)) {
            return ((Collection) obj).isEmpty();
        }
        if ((obj instanceof Map)) {
            return ((Map) obj).isEmpty();
        }
        return StringUtils.isEmpty(obj.toString());
    }

    /**
     * 判断是否为空对象
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 序列化对象
     */
    public static byte[] serializeObject(Object object) {
        try {
            ByteArrayOutputStream saos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(saos);
            oos.writeObject(object);
            oos.flush();
            return saos.toByteArray();
        } catch (Exception e) {
            log.debug("对象[" + object.getClass().getSimpleName() + "序列化异常！", e);
            return null;
        }
    }

    /**
     * 反序列化对象
     */
    public static Object deserializeObject(byte[] buf) {
        try {
            ByteArrayInputStream sais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(sais);
            return ois.readObject();
        } catch (Exception e) {
            log.debug("对象[" + buf.toString() + "反序列化异常！", e);
            return null;
        }
    }

    /**
     * @param objs 需要转换的对象
     * @return 转换后的bean
     * @Description: 对象转bean
     */
    public static <T> List<T> convert(List<Object> objs, Class<T> beanClass) {
        if (objs.size() > 0) {
            return objs.stream().map(obj -> (T) obj).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 对象转boolean
     */
    public static boolean objectToBoolean(Object obj) {
        if (isNotEmpty(obj)) {
            String bs = obj.toString();
            if ("1".equals(bs) || "y".equalsIgnoreCase(bs) || "on".equalsIgnoreCase(bs)
                    || "yes".equalsIgnoreCase(bs) || "true".equalsIgnoreCase(bs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象转类型转换
     */
    public static Object objectToFieldType(Object value, Class<?> fieldTypeClass) {
        String type = fieldTypeClass.getSimpleName();
        if ("long".equalsIgnoreCase(type)) {
            value = Long.parseLong(value.toString());
        } else if ("integer".equalsIgnoreCase(type) || "int".equalsIgnoreCase(type)) {
            value = Integer.parseInt(value.toString());
        } else if ("float".equalsIgnoreCase(type)) {
            value = Float.parseFloat(value.toString());
        } else if ("double".equalsIgnoreCase(type)) {
            value = Double.parseDouble(value.toString());
        } else if ("date".equalsIgnoreCase(type)) {
            value = new Date(Long.parseLong(value.toString()));
        } else if ("boolean".equalsIgnoreCase(type)) {
            value = ObjectUtils.objectToBoolean(value);
        }
        return value;
    }

    /**
     * 对象转HashMap
     */
    public static Map<String, Object> objectToHashMap(Object obj) {
        if (isNotEmpty(obj)) {
            Map<String, Object> result = new HashMap<>();
            Arrays.asList(obj.getClass().getDeclaredFields()).forEach(field -> {
                try {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Column.class)) {
                        Object value = field.get(obj);
                        if (ObjectUtils.isNotEmpty(value)) {
                            result.put(field.getAnnotation(Column.class).name(), field.get(obj));
                        }
                    }
                } catch (IllegalAccessException e) {
                    log.debug("对象[" + obj.getClass().getSimpleName() + "]参数[" + field.getName() + "]值获取失败", e);
                }
            });
            return result;
        }
        return null;
    }

    /**
     * HashMap转对象
     */
    public static <T> T hashMapToObject(Map<String, Object> map, Class<T> clz) {
        try {
            if (isNotEmpty(map)) {
                T bean = clz.newInstance();
                map.forEach((k, v) -> {
                    try {
                        Field field = getClassField(clz, k);
                        if (ObjectUtils.isNotEmpty(field)) {
                            Class<?> fieldTypeClass = field.getType();
                            v = objectToFieldType(v, fieldTypeClass);
                            String setMethodName = "set" + StringUtils.upperCaseFirst(k);
                            clz.getMethod(setMethodName, field.getType()).invoke(bean, v);
                        }
                    } catch (Exception e) {
                        log.debug(clz.getSimpleName() + "[" + k + "]设置异常", e);
                    }
                });
                return bean;
            }
        } catch (Exception e) {
            log.debug(clz.getSimpleName() + "实例化异常", e);
        }
        return null;
    }

    public static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {// 简单的递归一下
            return getClassField(superClass, fieldName);
        }
        return null;
    }

}
