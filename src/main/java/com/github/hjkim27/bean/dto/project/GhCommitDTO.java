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

    // commit message 제목
    private String title;

    // commit message 본문
    private String body;

    public String getTitle() {
        if (this.message != null) {
            return this.message.split("\\n\\n")[0];
        } else {
            return "";
        }
    }

    public String getBody() {
        if (this.message != null) {
            String[] arr = this.message.split("\\n\\n");
            if (arr.length > 1) {
                this.body = arr[1].replaceAll("\\n", "<br>");
                return this.body;
            }
        }
        return "";
    }

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
