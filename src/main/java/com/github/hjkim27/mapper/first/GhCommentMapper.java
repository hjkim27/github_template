package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhCommentDTO;
import com.github.hjkim27.bean.search.GhDetailSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * gh_comment mapper class
 *
 * @author hjkim27
 * @since 24.08.02
 */
@Mapper
public interface GhCommentMapper {

    /**
     * <pre>
     *     issue의 comment 추가
     * </pre>
     *
     * @param ghCommentDTO ghComment 정보
     */
    void insertRow(GhCommentDTO ghCommentDTO);


    /**
     * <pre>
     *     issue의 comment 업데이트
     *     - set : body, parent_comment_id, updated_at
     *     - where : comment_id
     * </pre>
     *
     * @param ghCommentDTO ghComment 정보
     */
    void updateRow(GhCommentDTO ghCommentDTO);

    /**
     * <pre>
     *     모든 comment 의 active=false 업데이트
     * </pre>
     *
     * @return update row count
     */
    Integer updateActiveFalse();

    /**
     * <pre>
     *     comment가 존재하는지 확인
     *     where : comment_id
     * </pre>
     *
     * @param ghCommentDTO ghComment 정보
     * @return check Exist
     */
    Boolean isExistRow(GhCommentDTO ghCommentDTO);

    /**
     * <pre>
     *     comment list 조회
     * </pre>
     *
     * @return ghComment 목록
     */
    List<GhCommentDTO> getList(GhDetailSearch search);

}
