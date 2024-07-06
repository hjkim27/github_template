package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     admin_info dto
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */
@Getter
@Setter
public class AdminInfoDTO {

    private Integer sid;
    private String loginId;
    private String loginPw;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;
}
