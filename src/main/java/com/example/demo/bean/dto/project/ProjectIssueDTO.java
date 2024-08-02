package com.example.demo.bean.dto.project;

import lombok.*;

import java.util.List;

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

    // 특정 issue 의 label_id 목록
    private List<Long> labelLIdList;

    // 특정 issue 의 comment 목록
    private List<ProjectCommentDTO> commentList;
}
