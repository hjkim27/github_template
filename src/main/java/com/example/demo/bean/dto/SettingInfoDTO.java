package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 *     setting_info
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@ToString
public class SettingInfoDTO {
    private Integer sid;
    private String key;
    private String value;
    private String type;
    private String description;
}
