package com.example.demo.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 *     로그: 합산
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */

@Getter
@Setter
@Builder
public class ClassifyLogCache {
    private int sid;
    private String appType;
    private int status;
    private boolean result;
    private Date createdAt;
}
