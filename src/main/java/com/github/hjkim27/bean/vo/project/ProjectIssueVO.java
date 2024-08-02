package com.github.hjkim27.bean.vo.project;


import lombok.*;

/**
 * <pre>
 *     tb_project_issue table
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectIssueVO {

    private Integer sid;
    private Integer repositorySid;
    private String labelIds;
    private String state;
    private Integer issueNumber;
    private String title;
    private String body;

}
