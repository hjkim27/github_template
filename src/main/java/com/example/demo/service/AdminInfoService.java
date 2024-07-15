package com.example.demo.service;

import com.example.demo.bean.dto.AdminRequestDTO;
import com.example.demo.bean.entity.AdminInfoEntity;
import com.example.demo.mapper.first.AdminInfoMapper;
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
    public AdminInfoEntity getAdminInfoBySid(int sid) {
        AdminInfoEntity entity = AdminInfoEntity.builder().sid(sid).build();
        return adminInfoMapper.getAdminInfoSearch(entity);
    }

    /**
     * <pre>
     *     loginId로 관리자정보 조회
     * </pre>
     *
     * @param loginId
     * @return
     */
    public AdminInfoEntity getAdminInfoByLoginId(String loginId) {
        AdminInfoEntity entity = AdminInfoEntity.builder().loginId(loginId).build();
        return adminInfoMapper.getAdminInfoSearch(entity);
    }

    /**
     * <pre>
     *     name 으로 관리자정보 조회
     * </pre>
     *
     * @param name
     * @return
     */
    public AdminInfoEntity getAdminInfoByName(String name) {
        AdminInfoEntity entity = AdminInfoEntity.builder().name(name).build();
        return adminInfoMapper.getAdminInfoSearch(entity);
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
        AdminInfoEntity entity = AdminInfoEntity.toEntity(adminRequestDTO);
        Integer sid = adminInfoMapper.getAdminSid(entity);
        return (sid != null) ? sid : -1;
    }

    private void updateAdminInfoBySid(AdminRequestDTO adminRequestDTO) {
        AdminInfoEntity entity = AdminInfoEntity.toEntity(adminRequestDTO);
        adminInfoMapper.updateAdminInfoBySid(entity);
    }

    private void updateAdminLoginAt(AdminRequestDTO adminRequestDTO) {
        AdminInfoEntity entity = AdminInfoEntity.toEntity(adminRequestDTO);
        adminInfoMapper.updateAdminLoginAt(entity);
    }

    private int insertAdminInfo(AdminRequestDTO adminRequestDTO) {
        AdminInfoEntity entity = AdminInfoEntity.toEntity(adminRequestDTO);
        return adminInfoMapper.insertAdminInfo(entity);
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
