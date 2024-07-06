package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *     setting_info dto
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
public class SettingInfoDTO {
    private int sid;
    private String key;
    private String value;
    private String description;
}
