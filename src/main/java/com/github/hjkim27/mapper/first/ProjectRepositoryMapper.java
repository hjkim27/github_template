package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.vo.project.ProjectRepositoryVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * tb_project_repository mapper class
 *
 * @author hjkim27
 * @since 24.07.30
 */
@Mapper
public interface ProjectRepositoryMapper {

    /**
     * <pre>
     *     repository 추가
     * </pre>
     *
     * @param projectRepositoryVO
     * @return
     */
    public Long insertRow(ProjectRepositoryVO projectRepositoryVO);

    /**
     * <pre>
     *     repository 업데이트
     *     - set : name, full_name, description, privacy, html_url, ssh_url, active(true)
     *     - where : full_name
     * </pre>
     *
     * @param projectRepositoryVO
     * @return
     */
    public Integer updateRow(ProjectRepositoryVO projectRepositoryVO);

    /**
     * <pre>
     *     모든 repository의 active=false 업데이트
     * </pre>
     *
     * @return
     */
    public Integer updateActiveFalse();

    /**
     * <pre>
     *     repository가 존재하는지 확인
     *     where : full_name
     * </pre>
     *
     * @param projectRepositoryVO
     * @return
     */
    public Boolean isExistRow(ProjectRepositoryVO projectRepositoryVO);
}
