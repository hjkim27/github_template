package com.example.demo.bean.vo;

import lombok.*;

import java.util.Date;

/**
 * <pre>
 *     tb_project_comment table
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
public class ProjectCommentVO {

    private Integer sid;
    private Integer issueSid;
    private String commentId;
    private String body;
    private String parentCommentId;
    private Date createdAt;
    private Date updatedAt;
    private Boolean active;
}
