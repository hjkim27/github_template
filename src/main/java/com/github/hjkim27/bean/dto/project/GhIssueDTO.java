package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     gh_issue
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
public class GhIssueDTO {

    private Integer sid;
    private Long ghId;
    private Integer issueNumber;
    private String title;
    private String body;
    private String state;
    private String htmlUrl;
    private String url;
    private Date createdAt;
    private Date updatedAt;
    private Date closedAt;

    private Long ghRepositoryId;
    private Integer repositorySid;

    private String labelIds;


    private String repositoryFullName;

    // 특정 issue 의 comment 목록
    private List<GhCommentDTO> commentList;

    // issue 목록 조회수정
    public List<Long> getLabelList() {
        List<Long> list = new ArrayList<>();
        if (!labelIds.isEmpty()) {
            String[] arr = labelIds.split(",");
            for (String s : arr) {
                list.add(Long.parseLong(s));
            }
        }
        return list;
    }
}
