package com.westbrain.sandbox.jaxrs.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.westbrain.sandbox.jaxrs.controller.ArticleController;
import com.westbrain.sandbox.jaxrs.exception.provider.BadRequestExceptionMapper;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CxfConfig {

    private final Bus bus;
    private final ArticleController articleController;

    @Autowired
    public CxfConfig(Bus bus, ArticleController articleController) {
        this.bus = bus;
        this.articleController = articleController;
    }

    @Bean
    public Server rsServer() {
        final JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setProviders(Arrays.<Object>asList(new JacksonJsonProvider(), new BadRequestExceptionMapper()));
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(List.<Object>of(articleController));
        endpoint.setFeatures(List.of(new Swagger2Feature()));
        return endpoint.create();
    }
}
