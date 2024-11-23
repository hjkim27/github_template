package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     gh_comment
 * </pre>
 *
 * @author hjkim27
 * @since 24.08.01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GhCommentDTO {

    private Integer sid;
    private Long ghId;
    private String body;
    private Long parentId;
    private Date createdAt;
    private Date updatedAt;
    private String htmlUrl;
    private String url;

    private Long ghOwnerId;
    private Long ghIssueId;

    private Integer ownerSid;
    private Integer issueSid;
    private Long ghRepositoryId;

    // event, comment 를 구분하기 위한 값.
    private final boolean eventType = false;
}
