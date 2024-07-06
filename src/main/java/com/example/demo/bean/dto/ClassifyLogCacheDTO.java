package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     classify_log_cache dto
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
public class ClassifyLogCacheDTO {
    private int sid;
    private String appType;
    private int status;
    private boolean result;
    private Date createdAt;
}
