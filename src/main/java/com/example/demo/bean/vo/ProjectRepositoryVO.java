package com.example.demo.bean.vo;

import lombok.*;

/**
 * <pre>
 *     tb_project_repository table
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
public class ProjectRepositoryVO {

    private Integer sid;
    private String name;
    private String fullName;
    private String description;
    private Boolean privacy;
    private String htmlUrl;
    private String sshUrl;
    private Boolean active;
}
