package com.example.demo.service;

import com.example.demo.bean.dto.project.ProjectLabelDTO;
import com.example.demo.bean.dto.project.ProjectRepositoryDTO;
import com.example.demo.bean.vo.project.ProjectLabelVO;
import com.example.demo.bean.vo.project.ProjectRepositoryVO;
import com.example.demo.mapper.first.ProjectLabelMapper;
import com.example.demo.mapper.first.ProjectRepositoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectLabelMapper projectLabelMapper;
    private final ProjectRepositoryMapper projectRepositoryMapper;
    private final ModelMapper modelMapper;

    /**
     * <pre>
     *     label_id 를 기준으로 존재여부를 확인, insert/update 진행
     * </pre>
     *
     * @param labelDTOList
     */
    public void insertLabels(List<ProjectLabelDTO> labelDTOList) {
        projectLabelMapper.updateActiveFalse();
        for (ProjectLabelDTO dto : labelDTOList) {
            ProjectLabelVO vo = modelMapper.map(dto, ProjectLabelVO.class);

            boolean isExistLabel = projectLabelMapper.isExistRow(vo.getLabelId());
            if (isExistLabel) {
                projectLabelMapper.updateRow(vo);
                log.info("update >> labelId : {}", vo.getLabelId());
            } else {
                projectLabelMapper.insertRow(vo);
                log.info("insert >> labelId : {}", vo.getLabelId());
            }
        }
    }


    /**
     * <pre>
     *     git repository link
     *     - full_name 을 기준으로 존재여부 확인, insert/update 진행
     * </pre>
     *
     * @param repositoryDTOList
     * @since 24.07.30
     */
    public void insertRepos(List<ProjectRepositoryDTO> repositoryDTOList) {
        projectRepositoryMapper.updateActiveFalse();
        for (ProjectRepositoryDTO dto : repositoryDTOList) {
            ProjectRepositoryVO vo = modelMapper.map(dto, ProjectRepositoryVO.class);

            boolean isExistRepo = projectRepositoryMapper.isExistRow(vo);
            if (isExistRepo) {
                projectRepositoryMapper.updateRow(vo);
                log.info("update >> fullName : {}", vo.getFullName());
            } else {
                projectRepositoryMapper.insertRow(vo);
                log.info("insert >> fullName : {}", vo.getFullName());

            }
        }
    }
}
