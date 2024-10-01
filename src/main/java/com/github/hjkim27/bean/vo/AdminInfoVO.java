package com.github.hjkim27.bean.vo;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 * create table admin_info (
 *     sid           serial,
 *     login_id      character varying not null,
 *     login_pw      character varying not null,
 *     name          character varying,
 *     created_at    Timestamp without time zone default now(),
 *     updated_at    Timestamp without time zone default now(),
 *     last_login_at timestamp without time zone,
 *     deleted_at    timestamp without time zone
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
public final class AdminInfoVO {

    private Integer sid;
    private String loginId;
    private String loginPw;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;
    private Date deletedAt;

    public AdminInfoVO(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public AdminInfoVO(String loginId) {
        this.loginId = loginId;
    }

    public AdminInfoVO(Integer sid) {
        this.sid = sid;
    }

}
