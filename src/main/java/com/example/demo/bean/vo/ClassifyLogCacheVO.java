package com.example.demo.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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
@AllArgsConstructor
public class ClassifyLogCacheVO {
    private int sid;
    private String appType;
    private int status;
    private boolean result;
    private Date createdAt;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ClassifyLogCacheVO vo = (ClassifyLogCacheVO) obj;

        return Objects.equals(sid, vo.sid)
                && Objects.equals(appType, vo.appType)
                && Objects.equals(status, vo.status)
                && Objects.equals(result, vo.result)
                && Objects.equals(createdAt, vo.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, appType, status, result, createdAt);
    }
}
