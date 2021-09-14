package com.westbrain.sandbox.jaxrs.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.westbrain.sandbox.jaxrs.exception.provider.BadRequestExceptionMapper;
import com.westbrain.sandbox.jaxrs.exception.provider.JsonParseExceptionMapper;
import com.westbrain.sandbox.jaxrs.exception.provider.ValidationExceptionMapper;
import com.westbrain.sandbox.jaxrs.service.impl.ArticleServiceImpl;
import com.westbrain.sandbox.jaxrs.service.impl.AuthorServiceImpl;
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
    private final ArticleServiceImpl articleService;
    private final AuthorServiceImpl authorService;

    @Autowired
    public CxfConfig(Bus bus, ArticleServiceImpl articleService, AuthorServiceImpl authorService) {
        this.bus = bus;
        this.articleService = articleService;
        this.authorService = authorService;
    }

    @Bean
    public Server rsServer() {
        final JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setProviders(Arrays.<Object>asList(new JacksonJsonProvider(),
                new BadRequestExceptionMapper(),
                new ValidationExceptionMapper(),
                new JsonParseExceptionMapper()));
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(List.<Object>of(articleService, authorService));
        endpoint.setFeatures(List.of(new Swagger2Feature()));
        return endpoint.create();
    }
}
