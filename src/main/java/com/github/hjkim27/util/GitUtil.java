package com.github.hjkim27.util;

import com.github.hjkim27.bean.dto.project.*;
import com.github.hjkim27.bean.type.CompareSignEnum;
import com.github.hjkim27.config.GeneralConfig;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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
    public List<ProjectLabelDTO> getLabels() throws IOException {
//        PagedSearchIterable<GHCommit> commits = getCommits(DateFormatUtil.getBeforeNDays(DateFormatUtil.DateFormat.yyyy_MM_dd, -7));
        Iterator<GHCommit> it = commits.iterator();

        List<ProjectLabelDTO> list = new ArrayList<>();
        if (it.hasNext()) {
            GHCommit commit = it.next();

            PagedIterable<GHLabel> labels = commit.getOwner().listLabels();
            Iterator it2 = labels.iterator();

            while (it2.hasNext()) {
                GHLabel label = (GHLabel) it2.next();
                ProjectLabelDTO dto = new ProjectLabelDTO();
                dto.setLabelId(label.getId());
                dto.setName(label.getName());
                dto.setDescription(label.getDescription());
                dto.setColor(label.getColor());

                // hjkim [2024-09-17] repository 에 따라 label 이 다를 경우 확인을 위함
                // [2024-09-22] fullName > id 로 변경
                dto.setGhRepositoryId(commit.getOwner().getId());
                list.add(dto);
            }
        }
        return list;
    }

    /**
     * <pre>
     *     commit 을 기준으로 repository 이름을 가져온다.
     *     FIXME 수정 필요. >> commit 이 없는 repository 는 조회할 수 없나..?
     *     - [2024.08.02] issue, comment, label(issue에 등록된) 관련 추가
     * </pre>
     *
     * @return
     * @throws IOException
     */
    public List<ProjectRepositoryDTO> getRepositorys() throws IOException {
//        PagedSearchIterable<GHCommit> commits = getCommits(DateFormatUtil.getBeforeNDays(DateFormatUtil.DateFormat.yyyy_MM_dd, -7));
        Iterator<GHCommit> it = commits.iterator();
        List<ProjectRepositoryDTO> list = new ArrayList<>();

        log.debug("commits.getTotalCount() : {}",commits.getTotalCount());
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
            GhRepositoryDTO dto = new GhRepositoryDTO();

            // repository ==
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

            // FIXME repository 소유주 추가 필요. 협업 repository 에 대한 내용도 추가되고 있어 구분이 필요함.
            // issue -----------
            List<GhIssueDTO> issueDTOList = new ArrayList<>();
            List<GHIssue> issues = commit.getOwner().getIssues(GHIssueState.ALL);
            for (GHIssue issue : issues) {

                // == issue ==
                GhIssueDTO issueDTO = new GhIssueDTO();
                issueDTO.setGhId(issue.getId());
                issueDTO.setIssueNumber(issue.getNumber());
                issueDTO.setTitle(issue.getTitle());
                issueDTO.setBody(issue.getBody());
                issueDTO.setState(issue.getState().name());
                issueDTO.setHtmlUrl(issue.getHtmlUrl().toString());
                issueDTO.setUrl(issue.getUrl().toString());
                issueDTO.setCreatedAt(issue.getCreatedAt());
                issueDTO.setUpdatedAt(issue.getUpdatedAt());
                issueDTO.setClosedAt(issue.getClosedAt());
                issueDTO.setGhRepositoryId(issue.getRepository().getId());

                // label_ids
                List<Long> labelIds = new ArrayList<>();
                Collection<GHLabel> labels = issue.getLabels();
                for (GHLabel label : labels) {
                    labelIds.add(label.getId());
                }
                issueDTO.setLabelIds(FormatUtil.listToString(labelIds, ","));


                // issue.comment ----------
                List<GhCommentDTO> commentDTOList = new ArrayList<>();
                List<GHIssueComment> comments = issue.getComments();
                for (GHIssueComment comment : comments) {

                    // == comment ==
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
                    commentDTOList.add(commentDTO);
                }
                issueDTO.setCommentList(commentDTOList);

                // issue.label ----------
                List<Long> labelIds = new ArrayList<>();
                Collection<GHLabel> labels = issue.getLabels();
                for (GHLabel label : labels) {
                    labelIds.add(label.getId());
                }
                issueDTO.setLabelIds(FormatUtil.listToString(labelIds, ","));
                issueDTOList.add(issueDTO);
            }
            log.debug("issues : {}", issueDTOList.size());
            dto.setIssueDTOList(issueDTOList);
            list.add(dto);
            log.debug("##### repoNames : {}", repoNames);
        }
        return list;
    }

    public List<ProjectCommitDTO> getCommit() throws IOException {
        Iterator<GHCommit> it = commits.iterator();

        List<ProjectCommitDTO> list = new ArrayList<>();

        log.info("== tb_project_commit ==========");
        while (it.hasNext()) {
            GHCommit commit = it.next();
            ProjectCommitDTO dto = new ProjectCommitDTO();
            dto.setRepositoryFullName(commit.getOwner().getFullName());
            dto.setSha(commit.getTree().getSha());
            List<String> parent = commit.getParentSHA1s();
            if (parent != null && !parent.isEmpty()) {
                dto.setParentSha(parent.get(0));
            }

            GitUser user = commit.getCommitShortInfo().getCommitter();
            dto.setCommitterName(user.getName());
            dto.setCommitterEmail(user.getEmail());
            dto.setCommitterDate(user.getDate());

            dto.setMessage(commit.getCommitShortInfo().getMessage());
            dto.setHtmlUrl(commit.getHtmlUrl().toString());
            list.add(dto);
        }
        return list;
    }
}
