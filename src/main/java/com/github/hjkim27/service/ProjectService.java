package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.ProjectIssueDTO;
import com.github.hjkim27.bean.dto.project.ProjectRepositoryDTO;
import com.github.hjkim27.bean.search.ProjectSearch;
import com.github.hjkim27.mapper.first.ProjectIssueMapper;
import com.github.hjkim27.mapper.first.ProjectRepositoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepositoryMapper repositoryMapper;
    private final ProjectIssueMapper issueMapper;

    public List<ProjectRepositoryDTO> getRepoList(ProjectSearch search) {
        return repositoryMapper.getList(search);
    }

    public List<ProjectIssueDTO> getIssueList(ProjectSearch search) {
        return issueMapper.getList(search);
    }
}
