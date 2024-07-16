package com.example.demo.mapper.first;

import com.example.demo.bean.vo.AdminInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminInfoMapper {

    public AdminInfoVO getAdminInfoSearch(AdminInfoVO adminInfoVO);

    /**
     * <pre>
     *     관리자 정보 조회 확인
     *     - loginId : 관리자 추가 시 중복확인
     *     - loginId, loginPw : 관리자 로그인 허용 확인
     * </pre>
     *
     * @param adminInfoVO
     * @return
     */
    public Integer getAdminSid(AdminInfoVO adminInfoVO);

    /**
     * <pre>
     *     관리자 정보 업데이트
     * </pre>
     *
     * @param adminInfoVO
     */
    public void updateAdminInfoBySid(AdminInfoVO adminInfoVO);

    /**
     * <pre>
     *     관리자 접속 시간 업데이트
     * </pre>
     *
     * @param adminInfoVO
     */
    public void updateAdminLoginAt(AdminInfoVO adminInfoVO);

    /**
     * <pre>
     *     관리자 추가
     * </pre>
     *
     * @param adminInfoVO
     */
    public int insertAdminInfo(AdminInfoVO adminInfoVO);

    /**
     * <pre>
     *     관리자 삭제
     * </pre>
     *
     * @param sids
     */
    public void deleteAdminInfo(@Param("sids") List<Integer> sids);
}
