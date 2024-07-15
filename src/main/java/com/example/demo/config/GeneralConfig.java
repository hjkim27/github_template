package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static final String START = "START";

    /**
     * 메인페이지
     */
    public static final String MAIN_URL = "/main";

    /**
     * 로그인페이지
     */
    public static final String SIGN_IN_URL = "/sign/sign-in";

    /**
     * 가입페이지
     */
    public static final String SIGN_UP_URL = "/sign/sign-up";
}
