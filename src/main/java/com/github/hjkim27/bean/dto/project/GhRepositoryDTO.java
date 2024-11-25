package com.github.hjkim27.bean.dto.project;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 *     gh_repository
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GhRepositoryDTO {

    private Integer sid;
    private Long ghId;
    private String name;
    private String fullName;
    private String description;
    private Boolean ghPrivate;
    private String language;
    private String htmlUrl;
    private String sshUrl;
    private String url;
    private Date createdAt;
    private Date updatedAt;

    private Boolean active;
    
    // repository 소유자 정보 추가
    private GhOwnerInfoDTO ghOwner;

}
