package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GhEventDTO {

    private Integer sid;
    private Long ghId;
    private String ghActorLogin;
    private String event;
    private String commitId;
    private String commitUrl;
    private String url;
    private Date createdAt;

    private Long ghIssueId;
    private Integer issueSid;
    private Long ghRepositoryId;

    // event, comment 를 구분하기 위한 값.
    private final boolean eventType = true;

    // event로 등록된 commit 의 message
    private String commitMessage;
}
