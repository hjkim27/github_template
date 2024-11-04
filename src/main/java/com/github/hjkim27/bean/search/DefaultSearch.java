package com.github.hjkim27.bean.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <PRE>
 * 검색에 사용될 값들을 갖고 있는 Bean 객체
 * </PRE>
 *
 * @author hjkim
 * @date 2024.08.19
 */
@Getter
@Setter
@ToString
public class DefaultSearch {

    private String searchKey;
    private String searchValue;

    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private int totalSize = 0;

    // 페이징 버튼 최대 출력 길이 제한
    private Integer pageInterval = 5;

    private String startDate;
    private String endDate;

    private int sortColumn = -1;
    private boolean desc = false;

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum > 1)
            this.pageNum = pageNum;
    }

    public Integer getLimit() {
        return pageSize;
    }

    public Integer getOffset() {
        if (pageNum == null || pageSize == null) {
            return null;
        }
        return (getPageNum() - 1) * pageSize;
    }

    public int getMinPage() {
        return ((pageNum - 1) / pageInterval) * pageInterval + 1;
    }

    public int getLastPage() {
        return totalSize / pageSize + (totalSize % pageSize != 0 ? 1 : 0);
    }
}
