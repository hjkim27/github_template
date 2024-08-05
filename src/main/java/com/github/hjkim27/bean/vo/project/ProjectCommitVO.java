package com.github.hjkim27.bean.vo.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     tb_project_commit table
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
public class ProjectCommitVO {

    private Integer sid;
    private Integer repositorySid;
    private String sha;
    private String parentSha;
    private String committerName;
    private String committerEmail;
    private Date committerDate;
    private String message;
    private String htmlUrl;
}
