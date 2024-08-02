package com.example.demo.bean.dto.project;

import lombok.*;

import java.util.List;

/**
 * <pre>
 *     tb_project_repository
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectRepositoryDTO {

    private String name;
    private String fullName;
    private String description;
    private Boolean privacy;
    private String htmlUrl;
    private String sshUrl;

    // 프로젝트에 속한 issue 목록
    private List<ProjectIssueDTO> issueDTOList;
}
