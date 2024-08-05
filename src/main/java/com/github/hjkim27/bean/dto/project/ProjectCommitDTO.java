package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     tb_project_commit
 * </pre>
 *
 * @author hjkim27
 * @since 2024.08.05
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectCommitDTO {

    private String repositoryFullName;
    private String sha;
    private String parentSha;
    private String committerName;
    private String committerEmail;
    private Date committerDate;
    private String message;
    private String htmlUrl;
}
