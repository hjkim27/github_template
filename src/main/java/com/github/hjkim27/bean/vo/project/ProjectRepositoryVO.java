package com.github.hjkim27.bean.vo.project;

import lombok.*;

/**
 * <pre>
 *     tb_project_repository table
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
public class ProjectRepositoryVO {

    private Integer sid;
    private String name;
    private String fullName;
    private String description;
    private Boolean privacy;
    private String htmlUrl;
    private String sshUrl;
    private Boolean active;

    public ProjectRepositoryVO(String name, String fullName, String description, Boolean privacy, String htmlUrl, String sshUrl) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.privacy = privacy;
        this.htmlUrl = htmlUrl;
        this.sshUrl = sshUrl;
    }
}
