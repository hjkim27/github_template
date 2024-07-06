package com.example.demo.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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
@AllArgsConstructor
public class ClassifyLogVO {

    private int sid;
    private String appType;
    private int status;
    private float score;
    private float threshold;
    private boolean result;
    private Date createdAt;
    private String token;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ClassifyLogVO vo = (ClassifyLogVO) obj;

        return Objects.equals(sid, vo.sid)
                && Objects.equals(appType, vo.appType)
                && Objects.equals(status, vo.status)
                && Objects.equals(score, vo.score)
                && Objects.equals(threshold, vo.threshold)
                && Objects.equals(result, vo.result)
                && Objects.equals(createdAt, vo.createdAt)
                && Objects.equals(token, vo.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, appType, status, score, threshold, result, createdAt, token);
    }
}
