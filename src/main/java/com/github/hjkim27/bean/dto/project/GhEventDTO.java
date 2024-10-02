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
    private Integer issueSid;
}
