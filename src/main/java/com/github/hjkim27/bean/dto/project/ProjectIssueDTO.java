package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.*;

/**
 * <pre>
 *     tb_project_issue
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
public class ProjectIssueDTO {
    private String repositoryFullName;
    private String state;
    private Integer issueNumber;
    private String title;
    private String body;
    private String labelIds;

    // 특정 issue 의 label_id 목록
    private List<String> labelLIdList;

    // [2024-08-28] issue 목록 반환 추가 수정 및 null 체크
    public List<String> getLabelIdList() {
        if (labelIds == null) {
            return Collections.emptyList();
        } else {
            String[] arr = labelIds.split(",");
            return Arrays.asList(arr);
        }
    }

    // 특정 issue 의 comment 목록
    private List<ProjectCommentDTO> commentList;
}
