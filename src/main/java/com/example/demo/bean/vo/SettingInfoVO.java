package com.example.demo.bean.vo;

import lombok.*;

/**
 * <pre>
 *     tb_setting_info table
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SettingInfoVO {

    private Integer sid;
    private String key;
    private String value;
    private String type;
    private String description;
    private Integer adminSid;

}
