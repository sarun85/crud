package com.arun.bean;

import java.io.IOException;
import java.util.List;

import com.arun.exception.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JacksonFactory {
  private static ObjectMapper mapper = new ObjectMapper();
  private static final Logger LOG = LoggerFactory.getLogger(JacksonFactory.class);

  static {
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public JacksonFactory() {
  }

  public static ObjectMapper getInstance() {
    return mapper;
  }

  public static String createJson(Object object) throws JsonParseException {
    try {
      return getInstance().writeValueAsString(object);
    } catch (IOException e) {
      LOG.error("Could not create JSON o:" + object, e);
      throw new JsonParseException();
    }
  }

  public static <T> T fromJson(String json, Class<T> theClass) throws JsonParseException {
    try {
      return getInstance().readValue(json, theClass);
    } catch (IOException e) {
      LOG.error("Could not create object from json:" + json, e);
      throw new JsonParseException();
    }
  }

  public static <T> List<T> listFromJson(String json, Class<T> clazz) throws JsonParseException {
    try {
      return getInstance().readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (IOException e) {
      LOG.error("Could not create object", e);
      throw new JsonParseException();
    }
  }

}
