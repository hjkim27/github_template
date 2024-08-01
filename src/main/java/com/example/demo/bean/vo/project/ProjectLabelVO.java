package com.example.demo.bean.vo.project;

import com.example.demo.bean.dto.project.ProjectLabelDTO;
import lombok.*;

import java.util.Date;

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
    private Long labelId;
    private String name;
    private String description;
    private String color;
    private Boolean active;

    public ProjectLabelVO(Long labelId, String name, String description, String color) {
        this.labelId = labelId;
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public ProjectLabelVO(ProjectLabelDTO dto) {
        this.labelId = dto.getLabelId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.color = dto.getColor();
    }

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
    public static class ProjectCommentVO {

        private Integer sid;
        private Integer issueSid;
        private Long commentId;
        private String body;
        private Long parentCommentId;
        private Date createdAt;
        private Date updatedAt;
        private Boolean active;
    }
}
