package com.github.hjkim27.bean.vo.project;

import lombok.*;

/**
 * <pre>
 *     tb_project_label table
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.16
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectLabelVO {

    private Integer sid;
    private Long labelId;
    private String name;
    private String description;
    private String color;
    private Boolean active;
}
