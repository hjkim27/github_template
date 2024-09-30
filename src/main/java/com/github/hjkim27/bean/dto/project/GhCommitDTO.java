package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     gh_commit
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
public class GhCommitDTO {

    private Integer sid;
    private String sha;
    private String parentSha;
    private String message;
    private Date commitDate;
    private Integer commentCount;
    private String htmlUrl;
    private String url;
    private Long ghRepositoryId;
    private Committer committer = new Committer();

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Committer {
        private String name;
        private String username;
        private String email;
        private Date committerDate;
    }
}
