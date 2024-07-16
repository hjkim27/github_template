package com.example.demo.bean.vo;


import lombok.*;

/**
 * <pre>
 *     tb_repository_issue table
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
public class RepositoryIssueVO {

    private Integer sid;
    private String labels;
    private String subject;
    private String content;
    private Integer repositorySid;

}
