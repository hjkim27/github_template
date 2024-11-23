package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.*;
import com.github.hjkim27.bean.search.GhDetailSearch;
import com.github.hjkim27.bean.search.GhSearch;
import com.github.hjkim27.config.GeneralConfig;
import com.github.hjkim27.mapper.first.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final GhRepositoryMapper repositoryMapper;
    private final GhIssueMapper issueMapper;
    private final GhLabelMapper labelMapper;
    private final GhCommitMapper commitMapper;
    private final GhCommentMapper commentMapper;
    private final GhEventMapper eventMapper;

    public List<GhRepositoryDTO> getRepoList(GhSearch search) {
        List<GhRepositoryDTO> list = repositoryMapper.getList(search);
        return (list.size() == 0) ? null : list;
    }

    public int getRepoTotalCount(GhSearch search) {
        return repositoryMapper.getTotalCount(search);
    }

    public List<GhIssueDTO> getIssueList(GhSearch search) {
        List<GhIssueDTO> list = issueMapper.getList(search);
        return (list.size() == 0) ? null : list;
    }

    public int getIssueTotalCount(GhSearch search) {
        return issueMapper.getTotalCount(search);
    }

    public List<GhLabelDTO> getLabelList(GhSearch search) {
        List<GhLabelDTO> list = labelMapper.getList(search);
        return (list.size() == 0) ? Collections.emptyList() : list;
    }

    public int getLabelTotalCount(GhSearch search) {
        return labelMapper.getTotalCount(search);
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

    /**
     * <pre>
     *     commit 목록 형식 변경
     * </pre>
     *
     * @param search
     * @return
     */
    public LinkedHashMap<String, List<GhCommitDTO>> getCommitsGroupDate(GhSearch search) {
        List<GhCommitDTO> list = getCommits(search);
        LinkedHashMap<String, List<GhCommitDTO>> map = new LinkedHashMap<>();
        List<GhCommitDTO> dtos = new ArrayList<>();
        for (GhCommitDTO dto : list) {
            String key = GeneralConfig.monthDay_yearFormat.format(dto.getCommitDate());
            dtos = (map.containsKey(key)) ? map.get(key) : new ArrayList<>();
            dtos.add(dto);
            map.put(key, dtos);
        }
        return map;
    }

    public int getCommitTotalCount(GhSearch search) {
        return commitMapper.getTotalCount(search);
    }

    public GhIssueDTO getIssue(GhDetailSearch search) {
        return issueMapper.getItem(search);
    }

    // issue 에 속한 comment 목록
    public Map<Date, GhCommentDTO> getCommentsByIssueId(GhDetailSearch search) {
        List<GhCommentDTO> list = commentMapper.getList(search);
        Map<Date, GhCommentDTO> map = new HashMap<>();
        for (GhCommentDTO dto : list) {
            map.put(dto.getCreatedAt(), dto);
        }
        return map;
    }

    // issue에 속한 event 목록
    public Map<Date, GhEventDTO> getEventsByIssueId(GhDetailSearch search) {
        List<GhEventDTO> list = eventMapper.getList(search);
        Map<Date, GhEventDTO> map = new HashMap<>();
        for (GhEventDTO dto : list) {
            map.put(dto.getCreatedAt(), dto);
        }
        return map;
    }

    // issue 에 속한 comment, event 를 생성시간기준으로 정렬한 list
    public List<Object> getCommented(GhDetailSearch search) {
        Map<Date, Object> map = new HashMap<>();
        map.putAll(getEventsByIssueId(search));
        map.putAll(getCommentsByIssueId(search));

        List<Object> list = map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());

        return list;
    }
}
