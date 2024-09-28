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

    private final ProjectLabelMapper projectLabelMapper;
    private final ProjectRepositoryMapper projectRepositoryMapper;
    private final ProjectIssueMapper projectIssueMapper;
    private final ProjectCommentMapper projectCommentMapper;
    private final ProjectCommitMapper projectCommitMapper;

    /**
     * <pre>
     *     git 연동 관련 active:false 업데이트 별도 메서드 분리
     * </pre>
     */
    public void updateActiveFalse() {
        projectLabelMapper.updateActiveFalse();
        projectRepositoryMapper.updateActiveFalse();
        projectCommentMapper.updateActiveFalse();
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

            boolean isExistLabel = projectLabelMapper.isExistRow(dto);
            if (isExistLabel) {
                projectLabelMapper.updateRow(dto);
            } else {
                projectLabelMapper.insertRow(dto);
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

            // [2024-09-17] isExistRow 반환타입 수정
            Integer repoSid = projectRepositoryMapper.isExistRow(dto);
            if (repoSid != null && repoSid > 0) {
                projectRepositoryMapper.updateRow(dto);
            } else {
                projectRepositoryMapper.insertRow(dto);
            }

            // issue 연동
            List<GhIssueDTO> issues = dto.getIssueDTOList();
            insertIssues(issues);
        }
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

            boolean isExistIssue = projectIssueMapper.isExistRow(dto);
            if (isExistIssue) {
                projectIssueMapper.updateRow(dto);
            } else {
                projectIssueMapper.insertRow(dto);
            }

            // comment 연동
            List<GhCommentDTO> comments = dto.getCommentList();
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

            boolean isExistComment = projectCommentMapper.isExistRow(dto);
            if (isExistComment) {
                projectCommentMapper.updateRow(dto);
            } else {
                projectCommentMapper.insertRow(dto);
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
    public void insertCommit(List<ProjectCommitDTO> commitDTOList) {
        for (ProjectCommitDTO dto : commitDTOList) {
            boolean isExistcommit = projectCommitMapper.isExistRow(dto);
            if (isExistcommit) {
                projectCommitMapper.updateRow(dto);
                log.info("update >> sha : {}", dto.getSha());
            } else {
                projectCommitMapper.insertRow(dto);
                log.info("insert >> sha : {}", dto.getSha());
            }
        }
    }
}
