package com.github.hjkim27.bean.vo;

import lombok.*;

/**
 * <pre>
 * create table setting_info (
 *     sid         serial,
 *     key         character varying unique not null,
 *     value       character varying,
 *     type        character varying,
 *     description character varying
 * );
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

}
