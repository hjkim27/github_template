package com.example.demo.service;

import com.example.demo.bean.dto.AdminRequestDTO;
import com.example.demo.bean.dto.AdminResponseDTO;
import com.example.demo.bean.vo.AdminInfo;
import com.example.demo.mapper.first.AdminInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminInfoService {

    private final AdminInfoMapper adminInfoMapper;

    public AdminInfo toVO(AdminRequestDTO adminRequestDTO) {
        return AdminInfo.builder()
                .loginId(adminRequestDTO.getLoginId())
                .loginPw(adminRequestDTO.getLoginPw())
                .name(adminRequestDTO.getName())
                .build();
    }

    public AdminResponseDTO toDTO(AdminInfo adminInfo) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setLoginId(adminInfo.getLoginId());
        dto.setLoginPw(adminInfo.getLoginPw());
        dto.setName(adminInfo.getName());
        dto.setCreatedAt(adminInfo.getCreatedAt());
        dto.setUpdatedAt(adminInfo.getUpdatedAt());
        dto.setLastLoginAt(adminInfo.getLastLoginAt());
        return dto;
    }

    /*======================================================*/

    private AdminResponseDTO getAdminInfoBySid(int sid) {
        AdminInfo info = adminInfoMapper.getAdminInfoBySid(sid);
        return toDTO(info);
    }

    private AdminResponseDTO getAdminInfoByLoginId(String loginId) {
        AdminInfo info = adminInfoMapper.getAdminInfoByLoginId(loginId);
        return toDTO(info);
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
        AdminInfo vo = toVO(adminRequestDTO);
        Integer sid = adminInfoMapper.getAdminSid(vo);
        return (sid != null) ? sid : -1;
    }

    private void updateAdminInfoBySid(AdminRequestDTO adminRequestDTO) {
        AdminInfo vo = toVO(adminRequestDTO);
        adminInfoMapper.updateAdminInfoBySid(vo);
    }

    private void updateAdminLoginAt(AdminRequestDTO adminRequestDTO) {
        AdminInfo vo = toVO(adminRequestDTO);
        adminInfoMapper.updateAdminLoginAt(vo);
    }

    private int insertAdminInfo(AdminRequestDTO adminRequestDTO) {
        AdminInfo vo = toVO(adminRequestDTO);
        return adminInfoMapper.insertAdminInfo(vo);
    }

    private void deleteAdminInfo(List<Integer> sids) {
        adminInfoMapper.deleteAdminInfo(sids);
    }

    /*--------------------------------------------------*/

    /**
     * <pre>
     *     존재하는 계정인지 확인
     * </pre>
     *
     * @param adminRequestDTO loginId 혹은 loginId, loginPw 가 설정된 {@link AdminRequestDTO}
     * @return 계정 존재여부
     */
    public boolean isExistAdmin(AdminRequestDTO adminRequestDTO) {
        return (getAdminSid(adminRequestDTO) > 0);
    }

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
        boolean isExist = isExistAdmin(adminRequestDTO);
        if (isExist) {
            log.info("Unable to register account. (Account definition exists)");
            return -1;
        } else {
            int result = insertAdminInfo(adminRequestDTO);
            updateAdminLoginAt(adminRequestDTO);
            return result;
        }
    }
}
