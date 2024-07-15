package com.example.demo.mapper.first;

import com.example.demo.bean.entity.AdminInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminInfoMapper {

    public AdminInfoEntity getAdminInfoSearch(AdminInfoEntity entity);

    /**
     * <pre>
     *     관리자 정보 조회 확인
     *     - loginId : 관리자 추가 시 중복확인
     *     - loginId, loginPw : 관리자 로그인 허용 확인
     * </pre>
     *
     * @param entity
     * @return
     */
    public Integer getAdminSid(AdminInfoEntity entity);

    /**
     * <pre>
     *     관리자 정보 업데이트
     * </pre>
     *
     * @param entity
     */
    public void updateAdminInfoBySid(AdminInfoEntity entity);

    /**
     * <pre>
     *     관리자 접속 시간 업데이트
     * </pre>
     *
     * @param entity
     */
    public void updateAdminLoginAt(AdminInfoEntity entity);

    /**
     * <pre>
     *     관리자 추가
     * </pre>
     *
     * @param entity
     */
    public int insertAdminInfo(AdminInfoEntity entity);

    /**
     * <pre>
     *     관리자 삭제
     * </pre>
     *
     * @param sids
     */
    public void deleteAdminInfo(@Param("sids") List<Integer> sids);
}
