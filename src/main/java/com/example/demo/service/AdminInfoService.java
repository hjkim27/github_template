package com.example.demo.service;

import com.example.demo.bean.dto.AdminRequestDTO;
import com.example.demo.bean.dto.AdminResponseDTO;
import com.example.demo.bean.vo.AdminInfo;
import com.example.demo.mapper.first.AdminInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
