package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhRepositoryDTO;
import com.github.hjkim27.bean.search.ProjectSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * gh_repository mapper class
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
     * @param ghRepositoryDTO ghRepository 정보
     */
    void insertRow(GhRepositoryDTO ghRepositoryDTO);

    /**
     * <pre>
     *     repository 업데이트
     *     where : ghId
     * </pre>
     *
     * @param ghRepositoryDTO ghRepository 정보
     */
    void updateRow(GhRepositoryDTO ghRepositoryDTO);

    /**
     * <pre>
     *     모든 repository의 active=false 업데이트
     * </pre>
     *
     * @return update row count
     */
    Integer updateActiveFalse();

    /**
     * <pre>
     *     repository 가 존재하는지 확인
     *     where : ghId or fullName
     * </pre>
     *
     * @param ghRepositoryDTO ghRepository 정보
     * @return tableSid
     */
    Integer isExistRow(GhRepositoryDTO ghRepositoryDTO);


    /**
     * <pre>
     *     repository list 조회
     * </pre>
     *
     * @return repository 정보 목록
     */
    List<GhRepositoryDTO> getList(ProjectSearch search);
}
