package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.ProjectIssueDTO;
import com.github.hjkim27.bean.dto.project.ProjectLabelDTO;
import com.github.hjkim27.bean.dto.project.ProjectRepositoryDTO;
import com.github.hjkim27.bean.search.ProjectSearch;
import com.github.hjkim27.mapper.first.ProjectIssueMapper;
import com.github.hjkim27.mapper.first.ProjectLabelMapper;
import com.github.hjkim27.mapper.first.ProjectRepositoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepositoryMapper repositoryMapper;
    private final ProjectIssueMapper issueMapper;
    private final ProjectLabelMapper labelMapper;

    public List<ProjectRepositoryDTO> getRepoList(ProjectSearch search) {
        return repositoryMapper.getList(search);
    }

    public List<ProjectIssueDTO> getIssueList(ProjectSearch search) {
        return issueMapper.getList(search);
    }

    public List<ProjectLabelDTO> getLabelList(ProjectSearch search) {
        return labelMapper.getList(search);
    }

    public Map<Long, ProjectLabelDTO> getLabelMap(ProjectSearch search) {
        List<ProjectLabelDTO> list = getLabelList(search);
        Map<Long, ProjectLabelDTO> map = new HashMap<>();
        for (ProjectLabelDTO dto : list) {
            map.put(dto.getLabelId(), dto);
        }
        return map;
    }
}
