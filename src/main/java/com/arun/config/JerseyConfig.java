package com.arun.config;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import com.arun.CrudApplication;
import com.arun.rest.CustomerResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

  private static final Logger log = LoggerFactory.getLogger(JerseyConfig.class);

  public JerseyConfig() {
    packages("com.arun");
    register(CustomerResource.class);
    property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
  }

  @Inject
  private ApplicationContext applicationContext;

  @PostConstruct
  public void init() {
    this.registerExceptionMappers();
  }

  private void registerExceptionMappers() {
    Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Provider.class);
    for (Object exceptionMapper : beans.values()) {
      log.info("Jersey exception mapper register: " + exceptionMapper.getClass().getName());
      register(exceptionMapper);
    }
  }
}
