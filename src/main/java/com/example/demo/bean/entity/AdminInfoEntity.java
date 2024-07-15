package com.example.demo.bean.entity;

import com.example.demo.bean.dto.AdminRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

/**
 * <pre>
 *     admin_info table
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
 */
@Getter
@Builder
@AllArgsConstructor
@ToString
public final class AdminInfoEntity {

    private final Integer sid;
    private final String loginId;
    private final String loginPw;
    private final String name;
    private final Date createdAt;
    private final Date updatedAt;
    private final Date lastLoginAt;
    private final Date deletedAt;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AdminInfoEntity vo = (AdminInfoEntity) obj;

        return Objects.equals(sid, vo.sid)
                && Objects.equals(loginId, vo.loginId)
                && Objects.equals(loginPw, vo.loginPw)
                && Objects.equals(name, vo.name)
                && Objects.equals(createdAt, vo.createdAt)
                && Objects.equals(updatedAt, vo.updatedAt)
                && Objects.equals(lastLoginAt, vo.lastLoginAt)
                && Objects.equals(deletedAt, vo.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, loginId, loginPw, name, createdAt, updatedAt, lastLoginAt, deletedAt);
    }

    public static AdminInfoEntity toEntity(AdminRequestDTO dto) {
        return AdminInfoEntity.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .build();
    }
}
