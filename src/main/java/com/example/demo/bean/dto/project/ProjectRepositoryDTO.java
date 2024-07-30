package com.example.demo.bean.dto.project;

import lombok.*;

/**
 * <pre>
 *     tb_project_repository
 * </pre>
 *
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

}
