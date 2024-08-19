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
public class ProjectRepositorySearch extends DefaultSearch {

    private String privacyType; // privacy column >> private or public

}
