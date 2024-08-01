package com.example.demo.bean.vo.project;


import lombok.*;

/**
 * <pre>
 *     tb_project_issue table
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
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
    private Integer number;
    private String title;
    private String body;
    private Boolean active;

}
