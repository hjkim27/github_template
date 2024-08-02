package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.vo.project.ProjectIssueVO;
import org.apache.ibatis.annotations.Mapper;


/**
 * tb_project_issue mapper class
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
     * @param projectIssueVO
     * @return
     */
    public Long insertRow(ProjectIssueVO projectIssueVO);

    /**
     * <pre>
     *     issue 업데이트
     *     - set : label_ids, state, title, body
     *     - where : issue_number
     * </pre>
     *
     * @param projectIssueVO
     * @return
     */
    public Integer updateRow(ProjectIssueVO projectIssueVO);

    /**
     * <pre>
     *     issue가 존재하는지 확인
     *     where : issue_number
     * </pre>
     *
     * @param projectIssueVO
     * @return
     */
    public Boolean isExistRow(ProjectIssueVO projectIssueVO);
}
