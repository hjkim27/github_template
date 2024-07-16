package com.example.demo.bean.vo;

import lombok.*;

/**
 * <pre>
 *     tb_repository_branch table
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
public class RepositoryBranchVO {

    private Integer sid;
    private String branchName;
    private String description;
    private Integer repositorySid;

}
