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
}
