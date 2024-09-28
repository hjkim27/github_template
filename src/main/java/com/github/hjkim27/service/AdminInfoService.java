package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.AdminRequestDTO;
import com.github.hjkim27.bean.vo.AdminInfoVO;
import com.github.hjkim27.mapper.first.AdminInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminInfoService {

    private final ModelMapper modelMapper;

    private final AdminInfoMapper adminInfoMapper;

    /**
     * <pre>
     *     sid로 관리자정보 조회
     * </pre>
     *
     * @param sid
     * @return
     */
    public AdminInfoVO getAdminInfoBySid(int sid) {
        AdminInfoVO adminInfoVO = new AdminInfoVO(sid);
        return adminInfoMapper.getAdminInfoSearch(adminInfoVO);
    }

    /**
     * <pre>
     *     loginId로 관리자정보 조회
     * </pre>
     *
     * @param loginId
     * @return
     */
    public AdminInfoVO getAdminInfoByLoginId(String loginId) {
        AdminInfoVO adminInfoVO = new AdminInfoVO(loginId);
        return adminInfoMapper.getAdminInfoSearch(adminInfoVO);
    }


    /**
     * <pre>
     * 관리자 정보 조회 확인
     *     - loginId : 관리자 추가 시 중복확인
     *     - loginId, loginPw : 관리자 로그인 허용 확인
     * </pre>
     *
     * @param adminRequestDTO
     * @return
     */
    public int getAdminSid(AdminRequestDTO adminRequestDTO) {
        AdminInfoVO adminInfoVO = modelMapper.map(adminRequestDTO, AdminInfoVO.class);
        Integer sid = adminInfoMapper.getAdminSid(adminInfoVO);
        return (sid != null) ? sid : -1;
    }

    private void updateAdminInfoBySid(AdminRequestDTO adminRequestDTO) {
        AdminInfoVO adminInfoVO = modelMapper.map(adminRequestDTO, AdminInfoVO.class);
        adminInfoMapper.updateAdminInfoBySid(adminInfoVO);
    }

    private void updateAdminLoginAt(AdminRequestDTO adminRequestDTO) {
        AdminInfoVO adminInfoVO = modelMapper.map(adminRequestDTO, AdminInfoVO.class);
        adminInfoMapper.updateAdminLoginAt(adminInfoVO);
    }

    private int insertAdminInfo(AdminRequestDTO adminRequestDTO) {
        AdminInfoVO adminInfoVO = modelMapper.map(adminRequestDTO, AdminInfoVO.class);
        return adminInfoMapper.insertAdminInfo(adminInfoVO);
    }

    private void deleteAdminInfo(List<Integer> sids) {
        adminInfoMapper.deleteAdminInfo(sids);
    }

    /*--------------------------------------------------*/

    public void updateAdmin(AdminRequestDTO adminRequestDTO) {
        updateAdminInfoBySid(adminRequestDTO);
    }

    /**
     * <pre>
     * 계정 추가
     * </pre>
     *
     * @param adminRequestDTO {@link AdminRequestDTO} 계정 추가를 위한 정보
     * @return 계정 추가 성공여부 (실패: -1 / 성공: admin_info.sid)
     */
    public int insertAdmin(AdminRequestDTO adminRequestDTO) {
        int adminSid = getAdminSid(adminRequestDTO);
        if (adminSid > 0) {
            log.info("Unable to register account. (Account definition exists)");
            return -1;
        } else {
            int result = insertAdminInfo(adminRequestDTO);
            updateAdminLoginAt(adminRequestDTO);
            return result;
        }
    }
}
