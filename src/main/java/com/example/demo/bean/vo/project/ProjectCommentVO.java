package com.example.demo.bean.vo.project;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     tb_project_comment table
 * </pre>
 *
 * @author hjkim27
 * @since 24.08.02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectCommentVO {

    private Integer sid;
    private Integer issueNumber;
    private Long commentId;
    private String body;
    private Long parentCommentId;
    private Date createdAt;
    private Date updatedAt;
    private Boolean active;
}
