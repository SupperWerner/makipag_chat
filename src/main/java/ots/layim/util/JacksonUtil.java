package ots.layim.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import ots.layim.myException.JsonUtilException;

import java.io.IOException;
import java.text.SimpleDateFormat;



public class JacksonUtil {

    private ObjectMapper mapper = new ObjectMapper();

    JacksonUtil() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
                                  SerializerProvider paramSerializerProvider) throws IOException {
                //设置返回null转为 空字符串""
                paramJsonGenerator.writeString("");
            }
        });
    }

    public static JacksonUtil create() {
        return new JacksonUtil();
    }


    public JacksonUtil setDateFormat(String pattern) {
        mapper.setDateFormat(new SimpleDateFormat(pattern));
        return this;
    }

    public JacksonUtil unwrapRootValue() {
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        return this;
    }

    public JacksonUtil wrapRootValue() {
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        return this;
    }

    public JacksonUtil setFilterProvider(FilterProvider filterProvider) {
        mapper.setFilterProvider(filterProvider);
        return this;
    }

    public JacksonUtil configure(DeserializationFeature feature, boolean state) {
        mapper.configure(feature, state);
        return this;
    }

    public JacksonUtil setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        mapper.setPropertyNamingStrategy(propertyNamingStrategy);
        return this;
    }

    public String objectToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonUtilException("Failed to convert object to json", e);
        }
    }

    public <T> T jsonToObject(String responseJson, Class<T> clazz) {
        try {
            return mapper.readValue(responseJson, clazz);
        } catch (IOException e) {
            throw new JsonUtilException("Failed to convert json to object", e);
        }
    }

    public <T> T jsonToObject(String responseJson, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(responseJson, valueTypeRef);
        } catch (IOException e) {
            throw new JsonUtilException("Failed to convert json to object", e);
        }
    }

    public JsonNode getJsonNode(String jsonStr) {
        try {
            return mapper.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new JsonUtilException("Failed to convert request to JsonNode",
                    e);
        } catch (IOException e) {
            throw new JsonUtilException("IO exception", e);
        }
    }

}