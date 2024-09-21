package com.github.hjkim27.service;

import com.github.hjkim27.bean.dto.project.*;
import com.github.hjkim27.bean.vo.project.ProjectCommentVO;
import com.github.hjkim27.bean.vo.project.ProjectIssueVO;
import com.github.hjkim27.bean.vo.project.ProjectLabelVO;
import com.github.hjkim27.bean.vo.project.ProjectRepositoryVO;
import com.github.hjkim27.mapper.first.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

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
     * @param labelDTOList
     */
    public void insertLabels(List<ProjectLabelDTO> labelDTOList) {
        for (ProjectLabelDTO dto : labelDTOList) {
            ProjectLabelVO vo = modelMapper.map(dto, ProjectLabelVO.class);

            // [2024-09-17] repositorySid 추가
            // [2024-09-22] sid 조회 방식 수정 (fullName > id)
            int repoSid = projectRepositoryMapper.isExistRow(new ProjectRepositoryVO(dto.getGhRepositoryId()));

            vo.setRepositorySid(repoSid);

            boolean isExistLabel = projectLabelMapper.isExistRow(vo);
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
        for (ProjectRepositoryDTO dto : repositoryDTOList) {
            ProjectRepositoryVO vo = modelMapper.map(dto, ProjectRepositoryVO.class);

            // [2024-09-17] isExistRow 반환타입 수정
            Integer repoSid = projectRepositoryMapper.isExistRow(vo);
            if (repoSid != null && repoSid > 0) {
                projectRepositoryMapper.updateRow(vo);
                log.info("update >> fullName : {}", vo.getFullName());
            } else {
                projectRepositoryMapper.insertRow(vo);
                log.info("insert >> fullName : {}", vo.getFullName());
            }

            // issue 연동
            List<ProjectIssueDTO> issues = dto.getIssueDTOList();
            insertIssues(issues, vo.getSid());
        }
    }

    /**
     * <pre>
     *     git issue link
     *     - issue_number 를 기준으로 존재여부 확인, insert/update 진행
     * </pre>
     *
     * @param issueDTOList
     * @param repositorySid
     * @since 24.08.02
     */
    public void insertIssues(List<ProjectIssueDTO> issueDTOList, Integer repositorySid) {
        for (ProjectIssueDTO dto : issueDTOList) {
            ProjectIssueVO vo = modelMapper.map(dto, ProjectIssueVO.class);
            vo.setRepositorySid(repositorySid);

            boolean isExistIssue = projectIssueMapper.isExistRow(vo);
            if (isExistIssue) {
                projectIssueMapper.updateRow(vo);
                log.info("update >> issueNumber : {}", vo.getIssueNumber());
            } else {
                projectIssueMapper.insertRow(vo);
                log.info("insert >> issueNumber : {}", vo.getIssueNumber());
            }

            // comment 연동
            List<ProjectCommentDTO> comments = dto.getCommentList();
            insertComment(comments, vo.getIssueNumber());
        }
    }

    /**
     * <pre>
     *     git comment link
     *     - comment_id 를 기준으로 존재여부 확인, insert/update 진행
     * </pre>
     *
     * @param commentDTOList
     * @since 24.08.02
     */
    public void insertComment(List<ProjectCommentDTO> commentDTOList, Integer issueNumber) {
        for (ProjectCommentDTO dto : commentDTOList) {
            ProjectCommentVO vo = modelMapper.map(dto, ProjectCommentVO.class);
            vo.setIssueNumber(issueNumber);

            boolean isExistComment = projectCommentMapper.isExistRow(vo);
            if (isExistComment) {
                projectCommentMapper.updateRow(vo);
                log.info("update >> commentId : {}", vo.getCommentId());
            } else {
                projectCommentMapper.insertRow(vo);
                log.info("insert >> commentId : {}", vo.getCommentId());
            }
        }
    }

    /**
     * <pre>
     *     git commit link
     *     - commit 의 sha 를 기준으로 존재여부를 확인해 insert/update 를 진행
     * </pre>
     *
     * @param commitDTOList
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
