package com.steven.common.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.*;

@Component
public class JsonUtil {
    private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static JsonUtil instance = new JsonUtil();
    private ObjectMapper mapper = new ObjectMapper();

    private JsonUtil(){
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public <T> T convertValue(Object fromValue, Class<T> toValueType){
        return mapper.convertValue(fromValue,toValueType);
    }

    public static JsonUtil getInstance(){
        return instance;
    }

    public String write(Object object) {
        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public Object read(String string){
        Object obj = null;
        try {
            obj = readObj(string);
        } catch (JsonParseException e) {
            LOG.error("解析出错"+string,e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Object readObj(String string) throws JsonParseException, JsonMappingException, IOException{
        string = string.trim();
        Object obj = null;
        Class cls = string.startsWith("[")? List.class: Map.class;
        obj = mapper.readValue(string,cls);
        return obj;
    }

    public <T> T read(String string,Class<T> clz){
        string = string.trim();
        T obj = null;
        try {
            obj = mapper.readValue(string,clz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

    public <T> T read(String json, TypeReference<T> jsonTypeReference) {
        try {
            return (T) mapper.readValue(json, jsonTypeReference);
        } catch (JsonParseException e) {
            LOG.error("decode(String, JsonTypeReference<T>)", e);
        } catch (JsonMappingException e) {
            LOG.error("decode(String, JsonTypeReference<T>)", e);
        } catch (IOException e) {
            LOG.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }

    //判断是不是JSON
    public static boolean isJSON(String str){
        if(isJSONObject(str)||isJSONArray(str)){
            return true;
        }else{
            return false;
        }
    }
    //判断是不是JSON对象
    public static boolean isJSONObject(String str) {
        // 此处应该注意,不要使用StringUtils.isEmpty(),
        // 因为当content为"  "空格字符串时，JSONObject.parseObject可以解析成功，
        if(StringUtils.isBlank(str)){
            return false;
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //判断是不是JSON数组
    public static boolean isJSONArray(String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        StringUtils.isEmpty(str);
        try {
            JSONArray jsonStr = JSONArray.parseArray(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
