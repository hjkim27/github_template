package com.example.demo.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * <pre>
 *     admin_info table
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 06
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public final class AdminInfo {

    private final Integer sid;
    private final String loginId;
    private final String loginPw;
    private final String name;
    private final Date createdAt;
    private final Date updatedAt;
    private final Date lastLoginAt;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AdminInfo vo = (AdminInfo) obj;

        return Objects.equals(sid, vo.sid)
                && Objects.equals(loginId, vo.loginId)
                && Objects.equals(loginPw, vo.loginPw)
                && Objects.equals(name, vo.name)
                && Objects.equals(createdAt, vo.createdAt)
                && Objects.equals(updatedAt, vo.updatedAt)
                && Objects.equals(lastLoginAt, vo.lastLoginAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, loginId, loginPw, name, createdAt, updatedAt, lastLoginAt);
    }
}
