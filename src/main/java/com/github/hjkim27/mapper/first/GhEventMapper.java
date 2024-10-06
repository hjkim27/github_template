package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhEventDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * gh_event mapper class
 *
 * @author hjkim27
 * @since 24.10.04
 */
@Mapper
public interface GhEventMapper {

    /**
     * <pre>
     *     issue의 event 추가
     * </pre>
     *
     * @param ghEventDTO ghEvent 정보
     */
    void insertRow(GhEventDTO ghEventDTO);


    /**
     * <pre>
     *     issue의 event 업데이트
     *     - where : gh_id
     * </pre>
     *
     * @param ghEventDTO ghEvent 정보
     */
    void updateRow(GhEventDTO ghEventDTO);

    /**
     * <pre>
     *     event가 존재하는지 확인
     *     where : gh_id
     * </pre>
     *
     * @param ghEventDTO ghEvent 정보
     * @return check Exist
     */
    Boolean isExistRow(GhEventDTO ghEventDTO);
}
