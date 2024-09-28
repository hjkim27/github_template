package com.github.hjkim27.bean.dto.project;

import lombok.*;

/**
 * <pre>
 *     gh_label
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
public class GhLabelDTO {

    private Integer sid;
    private Long ghId;
    private String name;
    private String description;
    private String color;
    private String url;

    private Long ghRepositoryId;
    private Integer repositorySid;
}
