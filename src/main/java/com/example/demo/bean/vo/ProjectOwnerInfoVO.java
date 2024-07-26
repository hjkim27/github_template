package com.example.demo.bean.vo;

import lombok.*;

/**
 * <pre>
 *     tb_project_owner_info table
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SHAPSHOT
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectOwnerInfoVO {

    private Integer sid;
    private String login;
    private String name;
    private String htmlUrl;
}
