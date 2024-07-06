package com.example.demo.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     로그: 상세
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
@Builder
public class ClassifyLog {

    private int sid;
    private String appType;
    private int status;
    private float score;
    private float threshold;
    private boolean result;
    private Date createdAt;
    private String token;
}
