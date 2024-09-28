package com.github.hjkim27.bean.vo.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 * create table gh_owner_info (
 *     sid        serial,
 *     gh_id      bigint unique not null,   -- commit.owner.owner.id
 *     name       character varying,        -- commit.owner.owner.name
 *     email      character varying,        -- commit.owner.owner.email
 *     login      character varying,        -- commit.owner.owner.login
 *     html_url   character varying,        -- commit.owner.owner.htmlUrl.toString
 *     url        character varying,        -- commit.owner.owner.url.toString
 *     created_at timestamp with time zone, -- commit.owner.owner.createdAt
 *     updated_at timestamp with time zone  -- commit.owner.owner.updatedAt
 * );
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SHAPSHOT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectOwnerInfoVO {

    private Integer sid;
    private Long ghId;
    private String name;
    private String email;
    private String login;
    private String htmlUrl;
    private String url;
    private Date createdAt;
    private Date updatedAt;

}
