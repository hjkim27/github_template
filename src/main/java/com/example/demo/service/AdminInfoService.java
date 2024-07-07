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

    public int getAdminSid(AdminRequestDTO dto) {
        AdminInfo vo = toVO(dto);
        Integer sid = adminInfoMapper.getAdminSid(vo);
        log.info("vo: {}", vo);
        log.info("sid: {}", sid);
        return (sid != null) ? sid : -1;
    }

    private void updateAdminInfoBySid(AdminRequestDTO dto) {
        AdminInfo vo = toVO(dto);
        adminInfoMapper.updateAdminInfoBySid(vo);
    }

    private void updateAdminLoginAt(AdminRequestDTO dto) {
        AdminInfo vo = toVO(dto);
        adminInfoMapper.updateAdminLoginAt(vo);
    }

    private void insertAdminInfo(AdminRequestDTO dto) {
        AdminInfo vo = toVO(dto);
        adminInfoMapper.insertAdminInfo(vo);
    }

    private void deleteAdminInfo(List<Integer> sids) {
        adminInfoMapper.deleteAdminInfo(sids);
    }

    /*--------------------------------------------------*/

    public boolean isExistAdmin(AdminRequestDTO dto) {
        return (getAdminSid(dto) > 0);
    }

    public void updateAdmin(AdminRequestDTO dto) {
        updateAdminInfoBySid(dto);
    }

    public boolean insertAdmin(AdminRequestDTO dto) {
        boolean isExist = isExistAdmin(dto);
        if (isExist) {
            log.info("Unable to register account. (Account definition exists)");
            return false;
        } else {
            insertAdminInfo(dto);
            updateAdminLoginAt(dto);
            return true;
        }
    }
}
