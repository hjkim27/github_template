package com.example.demo.mapper.first;

import com.example.demo.bean.vo.project.ProjectLabelVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * tb_project_label mapper class
 *
 * @author hjkim27
 * @since 24.07.28
 */
@Mapper
public interface ProjectLabelMapper {

    /**
     * <pre>
     *     라벨정보 추가
     * </pre>
     *
     * @param projectLabelVO
     * @return
     */
    public Long insertRow(ProjectLabelVO projectLabelVO);

    /**
     * <pre>
     *     라벨정보 업데이트
     *     - set : name, description, color, active(true)
     *     - where : label_id
     * </pre>
     *
     * @param projectLabelVO
     * @return
     */
    public Integer updateRow(ProjectLabelVO projectLabelVO);

    /**
     * <pre>
     *     모든 라벨의 active=false 업데이트
     * </pre>
     *
     * @return
     */
    public Integer updateActiveFalse();

    /**
     * <pre>
     *     라벨이 존재하는지 확인
     *     where : label_id
     * </pre>
     *
     * @param labelId
     * @return
     */
    public Boolean isExistRow(Long labelId);

    /**
     * <pre>
     *     issue번호로 issue 에 해당하는 label 목록 조회
     * </pre>
     *
     * @param issueNumber
     * @return
     */
    public List<ProjectLabelVO> getLabelsByIssueNumber(Integer issueNumber);
}
