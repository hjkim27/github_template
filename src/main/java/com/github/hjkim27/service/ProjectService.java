package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.ProjectRepositoryDTO;
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

    public List<ProjectRepositoryDTO> getRepoList(){
        return repositoryMapper.getRepoList();
    }
}
