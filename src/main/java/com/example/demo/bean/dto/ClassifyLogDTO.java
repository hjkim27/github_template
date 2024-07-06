package com.example.demo.bean.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     classify_log dto
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
public class ClassifyLogDTO {

    private int sid;
    private String appType;
    private int status;
    private float score;
    private float threshold;
    private boolean result;
    private Date createdAt;
    private String token;
}
