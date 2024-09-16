package com.github.hjkim27.bean.dto.project;

import lombok.*;

/**
 * <pre>
 *     tb_project_label
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectLabelDTO {

    private Long labelId;
    private String name;
    private String description;
    private String color;

    // hjkim [2024-09-17] repository 에 따라 label 이 다를 경우 확인을 위함
    private String repositoryFullName;
}
