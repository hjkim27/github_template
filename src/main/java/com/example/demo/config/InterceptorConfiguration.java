package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * @author hjkim27
 * @date 2024. 07. 07
 */
@Slf4j
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH";

    public static final String[] RESOURCE_PATTERNS = {
            "/static/**",
            "/resources/**",
            "/WEB-INF/**"
    };

    public static final String[] URL_PATTERNS = {
            "/**",
    };

    public static final String[] EXCLUDE_URL_PATTERNS = {
            "/sign/**",
            "/main/**",
            "/sse/**"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            registry.addInterceptor(new ProjectInterceptor())
                    .addPathPatterns(URL_PATTERNS)
                    .addPathPatterns("/")
                    .excludePathPatterns(RESOURCE_PATTERNS)
                    .excludePathPatterns(EXCLUDE_URL_PATTERNS);

            WebMvcConfigurer.super.addInterceptors(registry);

        } catch (Exception e) {
            log.error("INIT FAIL : addInterceptors() >> {} || {}", e.getMessage(), e);
            throw new BeanInitializationException("INIT FAIL : addInterceptors()");
        }
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            CacheControl control = CacheControl.maxAge(1, TimeUnit.DAYS).cachePublic();
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            WebMvcConfigurer.super.addResourceHandlers(registry);
        } catch (Exception e) {
            log.error("INIT FAIL : addResourceHandlers() >> {} | {}", e.getMessage(), e);
            throw new BeanInitializationException("INIT FAIL : addResourceHandlers()");
        }
    }

    /**
     * <pre>
     *     cros origin disable 처리
     * </pre>
     *
     * @param registry
     * @since 2024.01.23
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods(ALLOWED_METHOD_NAMES.split(","));
    }
}
