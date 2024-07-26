package com.example.demo.bean.vo;

import lombok.*;

/**
 * <pre>
 *     tb_project_label table
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
public class ProjectLabelVO {

    private Integer sid;
    private String labelId;
    private String name;
    private String description;
    private String color;
    private Boolean active;

}
