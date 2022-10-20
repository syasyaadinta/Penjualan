package com.tes.Penjualan.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver session = new SessionLocaleResolver();
        Locale indonesia = new Locale("id", "ID");
        session.setDefaultLocale(indonesia);
        return session;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/home/index");
        registry.addViewController("/home").setViewName("forward:/home/index");
//        registry.addViewController("/customer").setViewName("forward:/customer/index");
        registry.addViewController("/product").setViewName("forward:/product/index");
        registry.addViewController("/my-cart").setViewName("forward:/my-cart");
        registry.addViewController("/report").setViewName("forward:/report/index");

    }

}
