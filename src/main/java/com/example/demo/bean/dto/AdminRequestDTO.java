package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class AdminRequestDTO {

    private String loginId;
    private String loginPw;
    private String name;
}
