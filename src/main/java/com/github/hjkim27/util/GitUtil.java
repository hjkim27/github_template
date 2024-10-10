package com.github.hjkim27.util;

import com.github.hjkim27.bean.dto.project.*;
import com.github.hjkim27.bean.type.CompareSignEnum;
import com.github.hjkim27.config.GeneralConfig;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 *     git 정보를 연동하기 위한 util 클래스
 *     project, repository, issue, comment, label, commit 등의 이력을 조회한다.
 * </pre>
 *
 * @author hjkim27
 * @since 24.07.23
 */
@Slf4j
@Component
// FIXME properties 로 데이터를 가져오는건 되는데 github 연동 호출에서 문제가 발생함. 추가 확인 필요
//@PropertySource("classpath:github.properties")
public class GitUtil {

    private GitHub gitHub;

    // commit 목록을 불러오는 시간 단축을 위해 분리
    private PagedSearchIterable<GHCommit> commits;

    private final String token = "ghp_authToken";
    private final String userId = "user_id";

    private static Map<String, Integer> commitCounts = new LinkedHashMap<>();

//    @Value("${gitutil.git.token}")
//    private String token;
//    @Value("${gitutil.git.user-id}")
//    private String userId;

    public GitUtil() {
        try {
            gitHub = new GitHubBuilder().withAppInstallationToken(token).build();
            gitHub.checkApiUrlValidity();
            getCommits();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public GitHub getConnection() {
        return this.gitHub;
    }

    /**
     * @param committerDate 비교날짜
     * @param compareSign   등호 {@link CompareSignEnum} / (default {@link CompareSignEnum#upperAndEquals})
     * @return
     */
    public void getCommits(String committerDate, CompareSignEnum compareSign) {
        getConnection();
        GHSearchBuilder builder = null;
        if (committerDate != null) {
            if (compareSign == null) {
                compareSign = CompareSignEnum.upperAndEquals;
            }
            builder = gitHub.searchCommits()
                    .author(userId)
                    .committerDate(compareSign.getSign() + committerDate)
                    .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE);
        } else {
            builder = gitHub.searchCommits()
                    .author(userId)
                    .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE);
        }
        PagedSearchIterable<GHCommit> list = builder.list();
        commits = list;
    }

    /**
     * <pre>
     *     default compareSign : {@link CompareSignEnum#upperAndEquals}
     * </pre>
     *
     * @param committerDate 비교날짜
     * @return
     */
    public void getCommits(String committerDate) {
        getCommits(committerDate, CompareSignEnum.upperAndEquals);
    }

    public void getCommits() {
        getCommits(null, null);
    }

    /**
     * <pre>
     *     labels 연동
     *     FIXME 수정 필요.
     * </pre>
     *
     * @return
     * @throws IOException
     */
    public List<GhLabelDTO> getLabels() throws IOException {
        log.info(GeneralConfig.START);
        Iterator<GHCommit> it = commits.iterator();

        List<GhLabelDTO> list = new ArrayList<>();
        if (it.hasNext()) {
            GHCommit commit = it.next();

            PagedIterable<GHLabel> labels = commit.getOwner().listLabels();
            Iterator it2 = labels.iterator();

            while (it2.hasNext()) {
                GHLabel label = (GHLabel) it2.next();

                // == label ==
                GhLabelDTO dto = new GhLabelDTO();
                dto.setGhId(label.getId());
                dto.setName(label.getName());
                dto.setDescription(label.getDescription());
                dto.setColor(label.getColor());
                dto.setUrl(label.getUrl());
                dto.setGhRepositoryId(commit.getOwner().getId());

                list.add(dto);
            }
        }
        return list;
    }


    /**
     * <pre>
     *     issue 연동
     *     issue 에 속한 event, comment 포함
     * </pre>
     *
     * @return
     * @throws IOException
     * @since 2024-10-04
     */
    public List<GhIssueDTO> getIssues() throws IOException {
        log.info(GeneralConfig.START);
        Iterator<GHCommit> it = commits.iterator();

        List<GhIssueDTO> list = new ArrayList<>();
        if (it.hasNext()) {
            GHCommit commit = it.next();

            PagedIterable<GHIssue> issues = commit.getOwner().listIssues(GHIssueState.ALL);
            Iterator it2 = issues.iterator();

            while (it2.hasNext()) {
                GHIssue issue = (GHIssue) it2.next();
                // == issue ==
                GhIssueDTO issueDTO = new GhIssueDTO();
                issueDTO.setGhId(issue.getId());
                issueDTO.setIssueNumber(issue.getNumber());
                issueDTO.setTitle(issue.getTitle());
                issueDTO.setBody(issue.getBody());
                issueDTO.setState(issue.getState().name());
                issueDTO.setPullRequest(issue.isPullRequest());
                issueDTO.setLocked(issue.isLocked());
                issueDTO.setHtmlUrl(issue.getHtmlUrl().toString());
                issueDTO.setUrl(issue.getUrl().toString());
                issueDTO.setCreatedAt(issue.getCreatedAt());
                issueDTO.setUpdatedAt(issue.getUpdatedAt());
                issueDTO.setClosedAt(issue.getClosedAt());
                issueDTO.setGhRepositoryId(issue.getRepository().getId());

                List<Long> labelIds = issue.getLabels().stream()
                        .map(GHLabel::getId)
                        .collect(Collectors.toList());
                issueDTO.setLabelIds(FormatUtil.listToString(labelIds, ","));

                // == comment ==
                List<GHIssueComment> comments = issue.getComments();
                for (GHIssueComment comment : comments) {
                    GhCommentDTO commentDTO = new GhCommentDTO();
                    commentDTO.setGhId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    commentDTO.setParentId(comment.getParent().getId());
                    commentDTO.setCreatedAt(comment.getCreatedAt());
                    commentDTO.setUpdatedAt(comment.getUpdatedAt());
                    commentDTO.setHtmlUrl(comment.getHtmlUrl().toString());
                    commentDTO.setUrl(comment.getUrl().toString());
                    commentDTO.setGhOwnerId(comment.getUser().getId());
                    commentDTO.setGhIssueId(issue.getId());

                    issueDTO.addComment(commentDTO);
                }

                // == event ==
                for (GHIssueEvent event : issue.listEvents()) {
                    GhEventDTO eventDTO = new GhEventDTO();
                    eventDTO.setGhId(event.getId());
                    eventDTO.setGhActorLogin(event.getActor().getLogin());
                    eventDTO.setEvent(event.getEvent());
                    eventDTO.setCommitId(event.getCommitId());
                    eventDTO.setCommitUrl(event.getCommitUrl());
                    eventDTO.setUrl(event.getUrl());
                    eventDTO.setCreatedAt(event.getCreatedAt());
                    eventDTO.setGhIssueId(event.getIssue().getId());

                    issueDTO.addEvent(eventDTO);
                }
                list.add(issueDTO);
            }

        }
        return list;
    }

    /**
     * <pre>
     *     commit 을 기준으로 repository 이름을 가져온다.
     *     FIXME 수정 필요. >> commit 이 없는 repository 는 조회할 수 없나..?
     *     - [2024.08.02] issue, comment, label(issue에 등록된) 관련 추가
     *     - [2024.10.04] issue, comment 별도 메서드 분리
     * </pre>
     *
     * @return
     * @throws IOException
     */
    public List<GhRepositoryDTO> getRepositories() throws IOException {
        log.info(GeneralConfig.START);
        Iterator<GHCommit> it = commits.iterator();
        List<GhRepositoryDTO> list = new ArrayList<>();

        Set<String> repoNames = new HashSet<>();
        while (it.hasNext()) {
            GHCommit commit = it.next();
            // repository ----------
            GHRepository repository = commit.getOwner();
            String repoName = repository.getName();
            if (repoNames.contains(repoName)) {
                continue;
            } else {
                repoNames.add(repoName);
            }

            // repository ==
            GhRepositoryDTO dto = new GhRepositoryDTO();
            dto.setGhId(repository.getId());
            dto.setName(repository.getName());
            dto.setFullName(repository.getFullName());
            dto.setDescription(repository.getDescription());
            dto.setGhPrivate(repository.isPrivate());
            dto.setLanguage(repository.getLanguage());
            dto.setHtmlUrl(repository.getHtmlUrl().toString());
            dto.setSshUrl(repository.getSshUrl());
            dto.setUrl(repository.getUrl().toString());
            dto.setCreatedAt(repository.getCreatedAt());
            dto.setUpdatedAt(repository.getUpdatedAt());
            dto.setGhOwnerId(repository.getOwner().getId());

            // == owner ==
            GHUser user = repository.getOwner();
            GhOwnerInfoDTO ownerInfoDTO = new GhOwnerInfoDTO();
            ownerInfoDTO.setGhId(user.getId());
            ownerInfoDTO.setName(user.getName());
            ownerInfoDTO.setEmail(user.getEmail());
            ownerInfoDTO.setLogin(user.getLogin());
            ownerInfoDTO.setHtmlUrl(user.getHtmlUrl().toString());
            ownerInfoDTO.setUrl(user.getUrl().toString());
            ownerInfoDTO.setCreatedAt(user.getCreatedAt());
            ownerInfoDTO.setUpdatedAt(user.getUpdatedAt());

            dto.setGhOwner(ownerInfoDTO);

            list.add(dto);
        }
        return list;
    }

    public List<GhCommitDTO> getCommit() throws IOException {
        log.info(GeneralConfig.START);
        Iterator<GHCommit> it = commits.iterator();
        List<GhCommitDTO> list = new ArrayList<>();
        while (it.hasNext()) {
            GHCommit commit = it.next();

            // == commit ==
            GhCommitDTO dto = new GhCommitDTO();
            dto.setSha(commit.getTree().getSha());
            List<String> parent = commit.getParentSHA1s();
            if (parent != null && !parent.isEmpty()) {
                dto.setParentSha(parent.get(0));
            }
            dto.setCommitDate(commit.getCommitDate());
            dto.setCommentCount(commit.getCommitShortInfo().getCommentCount());
            dto.setMessage(commit.getCommitShortInfo().getMessage());
            dto.setHtmlUrl(commit.getHtmlUrl().toString());
            dto.setUrl(commit.getUrl().toString());

            GitUser user = commit.getCommitShortInfo().getCommitter();
            dto.setCommitter(new GhCommitDTO.Committer(user.getName(), user.getUsername(), user.getEmail(), user.getDate()));

            dto.setGhRepositoryId(commit.getOwner().getId());
            list.add(dto);
        }
        return list;
    }
}
