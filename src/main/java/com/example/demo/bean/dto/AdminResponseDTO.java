package com.example.demo.bean.dto;

import com.example.demo.bean.entity.AdminInfoEntity;
import lombok.Builder;
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
@Builder
public class AdminResponseDTO {

    private String loginId;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;

    public static AdminResponseDTO toDTO(AdminInfoEntity vo) {
        return AdminResponseDTO.builder()
                .loginId(vo.getLoginId())
                .name(vo.getName())
                .createdAt(vo.getCreatedAt())
                .updatedAt(vo.getUpdatedAt())
                .lastLoginAt(vo.getLastLoginAt())
                .build();
    }
}
