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

    // SHA 암호화 salt 문자열
    public static final String ENC_SALT = "hjkim27projectSalt!@##";

    public static final String START = "START";

    /**
     * 메인페이지
     */
    public static final String HOME_URL = "/projectRepository/main";

    /**
     * 로그인페이지
     */
    public static final String SIGN_IN_URL = "/sign/sign-in";

    /**
     * 가입페이지
     */
    public static final String SIGN_UP_URL = "/sign/sign-up";
}
