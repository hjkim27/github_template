package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhLabelDTO;
import com.github.hjkim27.bean.search.GhSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * gh_label mapper class
 *
 * @author hjkim27
 * @since 24.07.28
 */
@Mapper
public interface GhLabelMapper {

    /**
     * <pre>
     *     라벨정보 추가
     * </pre>
     *
     * @param ghLabelDTO ghLabel 정보
     */
    void insertRow(GhLabelDTO ghLabelDTO);

    /**
     * <pre>
     *     라벨정보 업데이트
     *     - set : name, description, color, active(true)
     *     - where : label_id
     * </pre>
     *
     * @param ghLabelDTO ghLabel 정보
     */
    void updateRow(GhLabelDTO ghLabelDTO);

    /**
     * <pre>
     *     모든 라벨의 active=false 업데이트
     * </pre>
     *
     * @return update row count
     */
    Integer updateActiveFalse();

    /**
     * <pre>
     *     라벨이 존재하는지 확인
     *     where : ghId
     * </pre>
     *
     * @param ghLabelDTO ghLabel 정보
     * @return check Exist
     */
    Boolean isExistRow(GhLabelDTO ghLabelDTO);

    /**
     * <pre>
     *     issue번호로 issue 에 해당하는 label 목록 조회
     * </pre>
     *
     * @param issueNumber label 을 조회할 ghIssue 번호
     * @return ghLabel 목록
     */
    List<GhLabelDTO> getLabelsByIssueNumber(Integer issueNumber);

    /**
     * <pre>
     *     issue list 조회
     * </pre>
     *
     * @return ghLabel 목록
     */
    List<GhLabelDTO> getList(GhSearch search);
}
