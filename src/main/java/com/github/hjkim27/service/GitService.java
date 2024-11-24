package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.*;
import com.github.hjkim27.mapper.first.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 *     git 연동 관련 로직을 정리한 service 클래스
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GitService {

    private final GhLabelMapper labelMapper;
    private final GhRepositoryMapper repositoryMapper;
    private final GhIssueMapper issueMapper;
    private final GhCommentMapper commentMapper;
    private final GhCommitMapper commitMapper;
    private final GhEventMapper eventMapper;
    private final GhOwnerInfoMapper ownerInfoMapper;

    /**
     * <pre>
     *     git 연동 관련 active:false 업데이트 별도 메서드 분리
     * </pre>
     */
    public void updateActiveFalse() {
        labelMapper.updateActiveFalse();
        repositoryMapper.updateActiveFalse();
        commentMapper.updateActiveFalse();
    }

    /**
     * <pre>
     *     git label link
     *     - label_id 를 기준으로 존재여부를 확인, insert/update 진행
     * </pre>
     *
     * @param labelDTOList label info list
     */
    public void insertLabels(List<GhLabelDTO> labelDTOList) {
        for (GhLabelDTO dto : labelDTOList) {

            boolean isExistLabel = labelMapper.isExistRow(dto);
            if (isExistLabel) {
                labelMapper.updateRow(dto);
            } else {
                labelMapper.insertRow(dto);
            }
        }
    }


    /**
     * <pre>
     *     git repository link
     *     - full_name 을 기준으로 존재여부 확인, insert/update 진행
     * </pre>
     *
     * @param repositoryDTOList repository info list
     * @since 24.07.30
     */
    public void insertRepos(List<GhRepositoryDTO> repositoryDTOList) {
        for (GhRepositoryDTO dto : repositoryDTOList) {

            // ghOwnerInfo
            int ownerSid = insertOwner(dto.getGhOwner());
            dto.setOwnerSid(ownerSid);

            // [2024-09-17] isExistRow 반환타입 수정
            Integer repoSid = repositoryMapper.isExistRow(dto);
            if (repoSid != null && repoSid > 0) {
                repositoryMapper.updateRow(dto);
            } else {
                repositoryMapper.insertRow(dto);
            }
        }
    }

    /**
     * <pre>
     *     git owner link
     * </pre>
     *
     * @param dto {@link GhOwnerInfoDTO}
     * @return gh_owner_info.sid
     * @since 2024.10.06
     */
    public int insertOwner(GhOwnerInfoDTO dto) {
        Integer ownerSid = ownerInfoMapper.isExistRow(dto);
        if (ownerSid != null && ownerSid > 0) {
            ownerInfoMapper.updateRow(dto);
        } else {
            ownerInfoMapper.insertRow(dto);
            ownerSid = dto.getSid();
        }
        return ownerSid;

    }


    /**
     * <pre>
     *     git issue link
     *     - issue_number 를 기준으로 존재여부 확인, insert/update 진행
     * </pre>
     *
     * @param issueDTOList issue info list
     * @since 24.08.02
     */
    public void insertIssues(List<GhIssueDTO> issueDTOList) {
        for (GhIssueDTO dto : issueDTOList) {

            boolean isExistIssue = issueMapper.isExistRow(dto);
            if (isExistIssue) {
                issueMapper.updateRow(dto);
            } else {
                issueMapper.insertRow(dto);
            }

            // event 연동
            List<GhEventDTO> events = dto.getEvents();
            insertEvent(events);

            // comment 연동
            List<GhCommentDTO> comments = dto.getComments();
            insertComment(comments);
        }
    }

    /**
     * <pre>
     *     git comment link
     *     - comment_id 를 기준으로 존재여부 확인, insert/update 진행
     * </pre>
     *
     * @param commentDTOList comment info list
     * @since 24.08.02
     */
    public void insertComment(List<GhCommentDTO> commentDTOList) {
        for (GhCommentDTO dto : commentDTOList) {

            boolean isExistComment = commentMapper.isExistRow(dto);
            if (isExistComment) {
                commentMapper.updateRow(dto);
            } else {
                commentMapper.insertRow(dto);
            }
        }
    }

    /**
     * <pre>
     *     git event link
     *     - event_id 를 기준으로 존재여부 확인, insert/udate 진행
     * </pre>
     *
     * @param eventDTOList
     */
    public void insertEvent(List<GhEventDTO> eventDTOList) {
        for (GhEventDTO dto : eventDTOList) {
            boolean isExistEvent = eventMapper.isExistRow(dto);
            if (isExistEvent) {
                eventMapper.updateRow(dto);
            } else {
                // repositorySid 조회 후 event 에 설정
                GhRepositoryDTO repositoryDTO = new GhRepositoryDTO();
                repositoryDTO.setGhId(dto.getGhRepositoryId());
                Integer repositorySid = repositoryMapper.isExistRow(repositoryDTO);
                dto.getLabel().setRepositorySid(repositorySid);
                eventMapper.insertRow(dto);
            }
        }
    }

    /**
     * <pre>
     *     git commit link
     *     - commit 의 sha 를 기준으로 존재여부를 확인해 insert/update 를 진행
     * </pre>
     *
     * @param commitDTOList commit info list
     * @since 2024.08.05
     */
    public void insertCommit(List<GhCommitDTO> commitDTOList) {
        for (GhCommitDTO dto : commitDTOList) {
            boolean isExistCommit = commitMapper.isExistRow(dto);
            if (isExistCommit) {
                commitMapper.updateRow(dto);
            } else {
                commitMapper.insertRow(dto);
            }
        }
    }
}
