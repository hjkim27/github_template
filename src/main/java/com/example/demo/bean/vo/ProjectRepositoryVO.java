package com.example.demo.bean.vo;

import lombok.*;

import java.util.Objects;

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
    private String repoName;
    private String description;
    private String repoType;
    private Integer adminSid;
}
