package com.github.hjkim27.bean.search;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     repository search class
 * </pre>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhSearch extends DefaultSearch {

    // repositories > privacy column >> private or public
    private String filterType;

    // filterType 검색에서 여러개 선택이 가능할 경우
    // issues > labelId
    private List<Object> filterTypeList;

    // [2024-09-16] issue, label, setting 을 repo별로 확인하기 위함
    private Integer repositorySid;

    public GhSearch(Integer repositorySid) {
        this.repositorySid = repositorySid;
    }

    // [2024-10-11] 서로 다른 column에서 searchValue 를 추가 검색하고자 할 경우 사용
    private List<String> searchValueList = new ArrayList<>();

}
