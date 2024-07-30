package com.example.demo.bean.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <pre>
 *     비교 연산자 enum
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.30
 */
@Getter
@AllArgsConstructor
public enum CompareSignEnum {
    upper(">"),
    upperAndEquals(">="),
    under("<"),
    underAndEquals("<="),
    equals("="),
    notEquals("!=");

    private final String sign;
}
