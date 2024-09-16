package com.github.hjkim27.bean.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
    private String filterType;

    // filterType 검색에서 여러개 선택이 가능할 경우
    // issues > labelId
    private List<Object> filterTypeList;

    // [2024-09-16] issue, label, setting 을 repo별로 확인하기 위함
    private Integer repositorySid;
}
