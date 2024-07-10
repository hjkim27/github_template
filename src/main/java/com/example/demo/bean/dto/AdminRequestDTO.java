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

    /**
     * 로그인 ID
     */
    private String loginId;

    /**
     * 로그인 PW
     */
    private String loginPw;

    /**
     * 로그인 PW 확인
     */
    private String loginPwCheck;

    /**
     * 이름
     */
    private String name;
}
