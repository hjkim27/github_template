package com.example.demo.mapper.first;

import com.example.demo.bean.vo.project.ProjectRepositoryVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hjkim27
 * @since 24.07.30
 */
@Mapper
public interface ProjectRepositoryMapper {

    public Long insertRow(ProjectRepositoryVO projectRepositoryVO);

    public Integer updateRow(ProjectRepositoryVO projectRepositoryVO);

    public Integer updateActiveFalse();

    public Boolean isExistRow(ProjectRepositoryVO projectRepositoryVO);
}
