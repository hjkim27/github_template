package com.example.demo.bean.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     tb_admin_info
 *     - select (결과)
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@Builder
public class AdminResponseDTO {

    private String loginId;
    private String name;
    private Date createdAt;
    private Date lastLoginAt;

}
