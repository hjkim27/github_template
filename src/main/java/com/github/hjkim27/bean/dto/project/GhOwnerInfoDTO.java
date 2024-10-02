package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GhOwnerInfoDTO {

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
