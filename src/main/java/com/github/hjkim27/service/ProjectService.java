package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.GhCommitDTO;
import com.github.hjkim27.bean.dto.project.GhIssueDTO;
import com.github.hjkim27.bean.dto.project.GhLabelDTO;
import com.github.hjkim27.bean.dto.project.GhRepositoryDTO;
import com.github.hjkim27.bean.search.GhDetailSearch;
import com.github.hjkim27.bean.search.GhSearch;
import com.github.hjkim27.mapper.first.GhCommitMapper;
import com.github.hjkim27.mapper.first.GhIssueMapper;
import com.github.hjkim27.mapper.first.GhLabelMapper;
import com.github.hjkim27.mapper.first.GhRepositoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final GhRepositoryMapper repositoryMapper;
    private final GhIssueMapper issueMapper;
    private final GhLabelMapper labelMapper;
    private final GhCommitMapper commitMapper;

    public List<GhRepositoryDTO> getRepoList(GhSearch search) {
        List<GhRepositoryDTO> list = repositoryMapper.getList(search);
        return (list.size() == 0) ? null : list;
    }

    public List<GhIssueDTO> getIssueList(GhSearch search) {
        List<GhIssueDTO> list = issueMapper.getList(search);
        return (list.size() == 0) ? null : list;
    }

    public List<GhLabelDTO> getLabelList(GhSearch search) {
        List<GhLabelDTO> list = labelMapper.getList(search);
        return (list.size() == 0) ? Collections.emptyList() : list;
    }

    public Map<Long, GhLabelDTO> getLabelMap(GhSearch search) {
        List<GhLabelDTO> list = getLabelList(search);
        return getLabelMap(list);
    }

    public Map<Long, GhLabelDTO> getLabelMap(List<GhLabelDTO> list) {
        Map<Long, GhLabelDTO> map = new HashMap<>();
        for (GhLabelDTO dto : list) {
            map.put(dto.getGhId(), dto);
        }
        return map;
    }

    public Map<String, Object> issueStateCount(Integer repositorySid) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Integer>> list = issueMapper.issueStateCount(repositorySid);

        for (Map<String, Integer> m : list) {
            map.put(String.valueOf(m.get("key")), m.get("value"));
        }
        return map;
    }

    public List<GhCommitDTO> getCommits(GhSearch search) {
        List<GhCommitDTO> list = commitMapper.getList(search);
        return (list.size() == 0) ? Collections.emptyList() : list;
    }

    public GhCommitDTO getCommit(GhDetailSearch search) {
        return commitMapper.getItem(search);
    }

}
