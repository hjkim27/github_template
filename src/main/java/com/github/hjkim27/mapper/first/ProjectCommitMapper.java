package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.ProjectCommitDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectCommitMapper {

    /**
     * <pre>
     *     commit 메시지 추가
     * </pre>
     *
     * @param projectCommitDTO
     * @return
     */
    public Long insertRow(ProjectCommitDTO projectCommitDTO);

    /**
     * <pre>
     *     commit 메시지 업데이트
     *     - set : name, description, color, active(true)
     *     - where : label_id
     * </pre>
     *
     * @param projectCommitDTO
     * @return
     */
    public Integer updateRow(ProjectCommitDTO projectCommitDTO);

    /**
     * <pre>
     *     commit 메시지가 존재하는지 확인
     *     where : sha
     * </pre>
     *
     * @param projectCommitDTO
     * @return
     */
    public Boolean isExistRow(ProjectCommitDTO projectCommitDTO);

}
