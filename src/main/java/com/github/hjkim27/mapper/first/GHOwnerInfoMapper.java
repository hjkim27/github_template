package com.github.hjkim27.mapper.first;

import com.github.hjkim27.bean.dto.project.GhOwnerInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GHOwnerInfoMapper {


    /**
     * <pre>
     *     gh사용자 정보 추가
     * </pre>
     *
     * @param ghOwnerInfoDTO ghOwnerInfo 정보
     */
    void insertRow(GhOwnerInfoDTO ghOwnerInfoDTO);

    /**
     * <pre>
     *     라벨정보 업데이트
     *     - where : gh_id
     * </pre>
     *
     * @param ghOwnerInfoDTO ghOwnerInfo 정보
     */
    void updateRow(GhOwnerInfoDTO ghOwnerInfoDTO);

    /**
     * <pre>
     *     gh사용자 정보가 존재하는지 확인
     *     where : ghId
     * </pre>
     *
     * @param ghOwnerInfoDTO ghOwnerInfo 정보
     * @return check Exist
     */
    Integer isExistRow(GhOwnerInfoDTO ghOwnerInfoDTO);

}
