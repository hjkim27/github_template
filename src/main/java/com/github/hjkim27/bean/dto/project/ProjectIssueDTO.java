package com.github.hjkim27.bean.dto.project;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
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
    private String labelIds;
    private Date createdAt;

    // 특정 issue 의 comment 목록
    private List<ProjectCommentDTO> commentList;

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
