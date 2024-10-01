package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhIssueDTO;
import com.github.hjkim27.bean.search.ProjectSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * gh_issue mapper class
 *
 * @author hjkim27
 * @since 24.08.02
 */
@Mapper
public interface ProjectIssueMapper {

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

}
