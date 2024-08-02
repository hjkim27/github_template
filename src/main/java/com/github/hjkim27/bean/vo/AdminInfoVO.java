package com.github.hjkim27.bean.vo;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     tb_admin_info table
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
