package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *     admin_info
 *     - insert / update / select(요청)
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */
@Getter
@Setter
public class AdminRequestDTO {

    private String loginId;
    private String loginPw;
    private String name;
}
