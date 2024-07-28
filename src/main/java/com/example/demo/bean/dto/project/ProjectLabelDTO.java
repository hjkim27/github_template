package com.example.demo.bean.dto.project;

import lombok.*;

/**
 * <pre>
 *     tb_project_label
 * </pre>
 *
 * @since 0.0.1-SNAPSHOT
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
