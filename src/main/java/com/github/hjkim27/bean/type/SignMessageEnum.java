package com.github.hjkim27.bean.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *     로그인/가입 등록 실패/성공에 대한 메시지 enum
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@AllArgsConstructor
public enum SignMessageEnum {
    // 비밀번호 불일치
    NOT_MATCH_PASSWORD(-1, "password does not matched", false),
    // 존재하지 않는 사용자
    NOT_EXIST_USER(-2, "does not exist user", false),
    // 사용 불가능한 ID
    UNABLE_USER_ID(-3, "does not available entered loginId", false),
    // 사용자 등록 실패
    FAILED_REGISTER_USER(-4, "Unable to register account. (Account definition exists)", false),
    // 성공
    SUCCESS(0, "success", true),
    // 성공
    FAIL(0, "occurs Encoding exception!!", true);

    // 에러코드
    final int status;

    // 에러메시지
    final String message;

    // 로그인 성공여부
    final boolean login;
}
