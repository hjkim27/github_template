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
    private String state;
    private Integer number;
    private String title;
    private String body;
    private List<Long> labelIds;
    private List<ProjectCommentDTO> commentList;
}
