package com.example.demo.mapper.first;

import com.example.demo.bean.vo.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminInfoMapper {

    /**
     * 관리자 정보 조회 by sid
     *
     * @param sid
     * @return
     */
    public AdminInfo getAdminInfoBySid(int sid);

    /**
     * 관리자 정보 조회 by loginId
     *
     * @param loginId
     * @return
     */
    public AdminInfo getAdminInfoByLoginId(String loginId);

    /**
     * <pre>
     *     관리자 정보 조회 확인
     *     - loginId : 관리자 추가 시 중복확인
     *     - loginId, loginPw : 관리자 로그인 허용 확인
     * </pre>
     *
     * @param adminInfo
     * @return
     */
    public Integer getAdminSid(AdminInfo adminInfo);

    /**
     * <pre>
     *     관리자 정보 업데이트
     * </pre>
     *
     * @param adminInfo
     */
    public void updateAdminInfoBySid(AdminInfo adminInfo);

    /**
     * <pre>
     *     관리자 접속 시간 업데이트
     * </pre>
     *
     * @param adminInfo
     */
    public void updateAdminLoginAt(AdminInfo adminInfo);

    /**
     * <pre>
     *     관리자 추가
     * </pre>
     *
     * @param adminInfo
     */
    public void insertAdminInfo(AdminInfo adminInfo);

    /**
     * <pre>
     *     관리자 삭제
     * </pre>
     *
     * @param sids
     */
    public void deleteAdminInfo(@Param("sids") List<Integer> sids);
}
