package com.example.demo.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     관리자 정보
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */
@Getter
@Setter
@Builder
public class AdminInfo {

    private Integer sid;
    private String loginId;
    private String loginPw;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;

    public AdminInfo(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}
