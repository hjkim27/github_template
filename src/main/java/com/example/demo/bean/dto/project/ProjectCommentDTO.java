package com.example.demo.bean.dto.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     tb_project_comomment
 * </pre>
 *
 * @author hjkim27
 * @since 24.08.01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectCommentDTO {

    private Long commentId;
    private String body;
    private Long parentCommentId;
    private Date createdAt;
    private Date updatedAt;
}
