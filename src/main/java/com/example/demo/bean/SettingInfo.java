package com.example.demo.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *     설정정보 저장
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
@Builder
public class SettingInfo {
    private int sid;
    private String key;
    private String value;
    private String description;
}
