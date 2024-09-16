package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;
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

    // [2024-09-16] repository 에 해당하는 issue, label, setting 을 확인하기 위해 sid 필요
    private Integer sid;

    private String name;
    private String fullName;
    private String description;
    private Boolean privacy;
    private String htmlUrl;
    private String sshUrl;
    private Date createdAt;
    private Date updatedAt;

    // 프로젝트에 속한 issue 목록
    private List<ProjectIssueDTO> issueDTOList;
}
