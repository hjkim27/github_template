package com.github.hjkim27.bean.search;

import lombok.*;

/**
 * <pre>
 *     repository Detail search class
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhDetailSearch extends DefaultSearch {

    private Integer repositorySid;

    private Integer sid;
    private String sha;

}
