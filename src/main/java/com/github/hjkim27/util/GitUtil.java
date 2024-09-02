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
                list.add(dto);
            }
            log.info("##### labelCount : {}", list.size());
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

        log.info("== tb_project_repository ==========");
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
            ProjectRepositoryDTO dto = new ProjectRepositoryDTO();
            dto.setName(repository.getName());
            dto.setFullName(repository.getFullName());
            dto.setDescription(repository.getDescription());
            dto.setPrivacy(repository.isPrivate());
            dto.setHtmlUrl(repository.getHtmlUrl().toString());
            dto.setSshUrl(repository.getSshUrl());
            dto.setCreatedAt(repository.getCreatedAt());
            dto.setUpdatedAt(repository.getUpdatedAt());
            // FIXME repository 소유주 추가 필요. 협업 repository 에 대한 내용도 추가되고 있어 구분이 필요함.
            log.info("repository : {}", dto.toString());
            log.info("{} >> issues ----------", dto.getName());
            // issue -----------
            List<ProjectIssueDTO> issueDTOList = new ArrayList<>();
            List<GHIssue> issues = commit.getOwner().getIssues(GHIssueState.ALL);
            for (GHIssue issue : issues) {
                ProjectIssueDTO issueDTO = new ProjectIssueDTO();
                issueDTO.setRepositoryFullName(commit.getOwner().getFullName());
                issueDTO.setState(issue.getState().name());
                issueDTO.setIssueNumber(issue.getNumber());
                issueDTO.setTitle(issue.getTitle());
                issueDTO.setBody(issue.getBody());
                issueDTO.setCreatedAt(issue.getCreatedAt());
//                log.info("issue : {}", issueDTO.toString());
//                log.info("{} | #{} >> comments ----------", dto.getName(), issueDTO.getIssueNumber());
                // issue.comment ----------
                List<ProjectCommentDTO> commentDTOList = new ArrayList<>();
                List<GHIssueComment> comments = issue.getComments();
                for (GHIssueComment comment : comments) {
                    ProjectCommentDTO commentDTO = new ProjectCommentDTO();
                    commentDTO.setCommentId(comment.getId());
                    commentDTO.setBody(comment.getBody());
                    commentDTO.setParentCommentId(comment.getParent().getId());
                    commentDTO.setCreatedAt(comment.getCreatedAt());
                    commentDTO.setUpdatedAt(comment.getUpdatedAt());
//                    log.info("comment : {}", commentDTO.toString());
                    commentDTOList.add(commentDTO);
                }
                issueDTO.setCommentList(commentDTOList);

                // issue.label ----------
//                log.info("tb labels >>>>>>>>>>");
                List<Long> labelIds = new ArrayList<>();
                Collection<GHLabel> labels = issue.getLabels();
                for (GHLabel label : labels) {
//                    log.info("label.id : {}", label.getId());
                    labelIds.add(label.getId());
                }
                issueDTO.setLabelIds(FormatUtil.listToString(labelIds, ","));
                issueDTOList.add(issueDTO);
            }
            dto.setIssueDTOList(issueDTOList);
            list.add(dto);
        }
        log.info("##### repoNames : {}", repoNames.toString());
        log.info("##### repoSize : {}", list.size());
        return list;
    }

    public List<ProjectCommitDTO> getCommit() throws IOException {
//        PagedSearchIterable<GHCommit> commits = getCommits();
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
            log.info("dto : {}", dto.toString());
            list.add(dto);
        }
        return list;
    }

    public void getCommits2() throws IOException {
        log.info(GeneralConfig.START);
        getConnection();

        GHSearchBuilder builder = gitHub.searchCommits()
                .author(userId)
                .committerDate(">=2024-07-17")        // 입력한 날짜 이후의 COMMIT 이력을 조회한다.
                .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE);

        PagedSearchIterable<GHCommit> commits = builder.list();

        Iterator<GHCommit> it = commits.iterator();
        int i = 0;
        while (it.hasNext()) {
            GHCommit commit = it.next();
            i++;
            String key = GeneralConfig.yyyyMMddFormat.format(commit.getCommitShortInfo().getCommitDate());
            if (commitCounts.containsKey(key)) {
                Integer value = commitCounts.get(key);
                commitCounts.put(key, ++value);
            } else {
                commitCounts.put(key, 1);
            }

            // repository
            log.info("");
            log.info("== tb_project_repository ==========");
            log.info("getOwner().getName() : {}", commit.getOwner().getName());                 // name
            log.info("getOwner().getFullName() : {}", commit.getOwner().getFullName());         // full_name
            log.info("getOwner().getDescription() : {}", commit.getOwner().getDescription());   // description
            log.info("getOwner().isPrivate() : {}", commit.getOwner().isPrivate());             // private
            log.info("getOwner().getHtmlUrl() : {}", commit.getOwner().getHtmlUrl());           // html_url
            log.info("getOwner().getSshUrl() : {}", commit.getOwner().getSshUrl());             // ssh_url

            // user
            log.info("");
            log.info("== tb_project_owner ==========");
            log.info("getOwner().getOwner.login() : {}", commit.getOwner().getOwner().getLogin());          // login
            log.info("getOwner().getOwner.name() : {}", commit.getOwner().getOwner().getName());            // name
            log.info("getOwner().getOwner.htmlUrl() : {}", commit.getOwner().getOwner().getHtmlUrl());      // html_url

            // commit
            log.info("");
            log.info("== tb_project_commit ==========");
            log.info("getTree.sha() : {}", commit.getTree().getSha());              // sha
            log.info("getParentSHA1s() : {}", commit.getParentSHA1s().get(0));      // upper_sha
            log.info("-- ShortInfo ----------");
            log.info("sortInfo.committer().name() : {}", commit.getCommitShortInfo().getCommitter().getName());     // committer_name
            log.info("sortInfo.committer().email() : {}", commit.getCommitShortInfo().getCommitter().getEmail());   // committer_email
            log.info("sortInfo.committer().date() : {}", commit.getCommitShortInfo().getCommitter().getDate());     // committer_date
            log.info("sortInfo.message : {}", commit.getCommitShortInfo().getMessage());        // commit message
            log.info("------------------------");
            log.info("htmlUrl : {}", commit.getHtmlUrl()); // html_url

            // issue
            log.info("");
            log.info("\t == issue ==========");
            List<GHIssue> issues = commit.getOwner().getIssues(GHIssueState.ALL);
            for (GHIssue issue : issues) {
                log.info("-----");
                log.info("state : {}", issue.getState());
                log.info("number : {}", issue.getNumber());
                log.info("title : {}", issue.getTitle());
                log.info("body : {}", issue.getBody());

                // issue comment
                log.info("");
                log.info("\t == issue.comment ==========");
                List<GHIssueComment> comments = issue.getComments();
                for (GHIssueComment com : comments) {
                    log.info("\tcommentId : {}", com.getId());
                    log.info("\tcommentBody : {}", com.getBody());
                    log.info("\tcommentParentId : {}", com.getParent().getId());
                    log.info("\tcommentCreatedAt : {}", com.getCreatedAt());
                    log.info("\tcommentUpdatedAt : {}", com.getUpdatedAt());
                }
                log.info("");
                log.info("\t == issue.label ==========");
                Collection<GHLabel> labels = issue.getLabels();
                for (GHLabel label : labels) {
                    log.info("\tlabelId : {}", label.getId());
                    log.info("\tlabelName : {}", label.getName());
                    log.info("\tlabelDescription : {}", label.getDescription());
                    log.info("\tlabelColor : {}", label.getColor());
                }

            }
            log.info("-----");

            PagedIterable<GHLabel> labels = commit.getOwner().listLabels();
            Iterator it2 = labels.iterator();

            log.info("labels ==========");
            while (it2.hasNext()) {
                GHLabel label = (GHLabel) it2.next();
                log.info("labelId : {}", label.getId());
                log.info("labelName : {}", label.getName());
                log.info("labelDescription : {}", label.getDescription());
                log.info("labelColor : {}", label.getColor());
                log.info("-----");
            }
            break;
            /* example
                commitDate      : 2024-07-23
                getMessage      : [FIX] logback 수정  <br><br>  - 로그가 중복으로 찍히던 문제 수정
                getTreeSHA1     : 69c51aedcc50aaad87b13ac85e6457585e6bce87
                getHtmlUrl      : https://github.com/hjkim27/board_template/commit/8b9cb239693c88b39895b90020ec3e2feee11535
                getFullName     : hjkim27/board_template
                getOwnerName    : hjkim27
                getName         : board_template
             */
        }

        log.info("total {}", i);

    }
}
