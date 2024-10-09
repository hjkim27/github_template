package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhIssueDTO;
import com.github.hjkim27.bean.search.ProjectSearch;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * gh_issue mapper class
 *
 * @author hjkim27
 * @since 24.08.02
 */
@Mapper
public interface GhIssueMapper {

    /**
     * <pre>
     *     issue 추가
     * </pre>
     *
     * @param ghIssueDTO ghIssue 정보
     */
    void insertRow(GhIssueDTO ghIssueDTO);

    /**
     * <pre>
     *     issue 업데이트
     *     - where : ghId
     * </pre>
     *
     * @param ghIssueDTO ghIssue 정보
     */
    void updateRow(GhIssueDTO ghIssueDTO);

    /**
     * <pre>
     *     issue가 존재하는지 확인
     *     where : ghId or issueNumber
     * </pre>
     *
     * @param ghIssueDTO ghIssue 정보
     * @return check Exist
     */
    Boolean isExistRow(GhIssueDTO ghIssueDTO);

    /**
     * <pre>
     *     issue list 조회
     * </pre>
     *
     * @return 프로젝트에 속한 issue 목록
     */
    List<GhIssueDTO> getList(ProjectSearch search);


    /**
     * <pre>
     *     issue 상태에 따른 count 조회
     *     pull_request 여부, state 값을 기준으로 issue 수 확인
     * </pre>
     *
     * @param repositorySid
     * @return issue count
     */
    @MapKey("key")
    List<Map<String, Integer>> issueStateCount(Integer repositorySid);
}
