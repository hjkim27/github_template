package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *     로그인/가입 등록 실패/성공에 대한 메시지 enum
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 07
 */
@Getter
@AllArgsConstructor
public enum SignMessageEnum {
    // 비밀번호 불일치
    NOT_MATCH_PASSWORD(-2, "password does not matched", false),
    // 존재하지 않는 사용자
    NOT_EXIST_USER(-1, "does not exist user", false),
    // 사용 불가능한 ID
    NOT_USED_ID(-10, "does not available entered loginId", false),
    // 성공
    SUCCESS(0, "success", true);

    // 에러코드
    final int status;

    // 에러메시지
    final String message;

    // 로그인 성공여부
    final boolean login;
}
