package com.example.demo.service;

import com.example.demo.bean.dto.project.ProjectLabelDTO;
import com.example.demo.bean.vo.project.ProjectLabelVO;
import com.example.demo.mapper.first.ProjectLabelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProjectService {

    private final ProjectLabelMapper projectLabelMapper;
    private final ModelMapper modelMapper;

    /**
     * <pre>
     *     label_id 를 기준으로 존재여부를 확인, insert/update 진행
     * </pre>
     *
     * @param labelDTOList
     */
    public void insertLabels(List<ProjectLabelDTO> labelDTOList) {
        projectLabelMapper.updateLabelActiveFalse();
        for (ProjectLabelDTO dto : labelDTOList) {
            ProjectLabelVO vo = modelMapper.map(dto, ProjectLabelVO.class);

            boolean isExistLabel = projectLabelMapper.isExistLabel(vo.getLabelId());
            if (isExistLabel) {
                projectLabelMapper.updateLabel(vo);
                log.info("update >> labelId : {}", vo.getLabelId());
            } else {
                projectLabelMapper.insertLabel(vo);
                log.info("insert >> labelId : {}", vo.getLabelId());
            }
        }
    }
}
