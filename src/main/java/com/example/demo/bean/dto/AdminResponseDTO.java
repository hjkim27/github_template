package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     admin_info
 *     - select (결과)
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */
@Getter
@Setter
public class AdminResponseDTO {

    private String loginId;
    private String loginPw;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;
}
