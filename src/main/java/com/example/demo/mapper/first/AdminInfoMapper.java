package com.example.demo.mapper.first;

import com.example.demo.bean.dto.AdminRequestDTO;
import com.example.demo.bean.dto.AdminResponseDTO;
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
    public AdminResponseDTO getAdminInfoBySid(int sid);

    /**
     * 관리자 정보 조회 by loginId
     *
     * @param loginId
     * @return
     */
    public AdminResponseDTO getAdminInfoByLoginId(String loginId);

    /**
     * <pre>
     *     관리자 정보 조회 확인
     *     - loginId : 관리자 추가 시 중복확인
     *     - loginId, loginPw : 관리자 로그인 허용 확인
     * </pre>
     *
     * @param adminInfoDTO
     * @return
     */
    public int isExistAdmin(AdminRequestDTO adminInfoDTO);

    /**
     * <pre>
     *     관리자 정보 업데이트
     * </pre>
     *
     * @param adminInfoDTO
     */
    public void updateAdminInfo(AdminRequestDTO adminInfoDTO);

    /**
     * <pre>
     *     관리자 추가
     * </pre>
     *
     * @param adminInfoDTO
     */
    public void insertAdminInfo(AdminRequestDTO adminInfoDTO);

    /**
     * <pre>
     *     관리자 삭제
     * </pre>
     *
     * @param sids
     */
    public void deleteAdminInfo(@Param("sids") List<Integer> sids);
}
