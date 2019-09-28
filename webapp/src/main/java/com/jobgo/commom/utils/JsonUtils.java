package com.jobgo.commom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.Nullable;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 描述：Json工具类
 * 创建时间: 2019-08-16 15:31
 * 修改时间: 2019-08-16 15:31
 */
@Slf4j
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 描述：序列化，Json对象转字符串
     *
     * @param obj
     * @return
     */
    @Nullable
    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj.getClass() == String.class) {
            return (String) obj;
        }

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    /**
     * 描述：解析Json字符串，字符串转Json对象
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    @Nullable
    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            return null;
        }
    }


    /**
     * 描述：解析Json字符串列表，字符串转列表
     * @param json
     * @param eClass
     * @param <E>
     * @return
     */
    @Nullable
    public static <E> List<E> parseList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            return null;
        }
    }


    /**
     * 描述：解析Json字符串映射，字符串转映射
     * @param json
     * @param kClass
     * @param vClass
     * @param <K>
     * @param <V>
     * @return
     */
    @Nullable
    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            return null;
        }
    }

 
    @Nullable
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            return null;
        }
    }
}
