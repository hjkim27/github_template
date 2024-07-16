package com.example.demo.bean.vo;

import lombok.*;

/**
 * <pre>
 *     tb_repository_label table
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
public class RepositoryLabelVO {

    private Integer sid;
    private String labelName;
    private String description;
    private String labelColor;
    private String labelBgColor;

}
