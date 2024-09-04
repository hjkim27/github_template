package com.github.hjkim27.bean.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 *     repository search class
 * </pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ProjectSearch extends DefaultSearch {

    // repositories > privacy column >> private or public
    // issues > labelId
    private String filterType;

}
