package com.example.demo.util;

import com.example.demo.bean.dto.project.ProjectLabelDTO;
import com.example.demo.bean.dto.project.ProjectRepositoryDTO;
import com.example.demo.bean.type.CompareSignEnum;
import com.example.demo.config.GeneralConfig;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
// FIXME properties 로 데이터를 가져오는건 되는데 github 연동 호출에서 문제가 발생함. 추가 확인 필요
//@PropertySource("classpath:github.properties")
public class GitUtil {

    private GitHub gitHub;

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
    public PagedSearchIterable<GHCommit> getCommits(String committerDate, CompareSignEnum compareSign) {
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
        log.info("commit >> list.getTotalCount() : {}", list.getTotalCount());
        return list;
    }

    /**
     * <pre>
     *     default compareSign : {@link CompareSignEnum#upperAndEquals}
     * </pre>
     *
     * @param committerDate 비교날짜
     * @return
     */
    public PagedSearchIterable<GHCommit> getCommits(String committerDate) {
        return getCommits(committerDate, CompareSignEnum.upperAndEquals);
    }

    public PagedSearchIterable<GHCommit> getCommits() {
        return getCommits(null, null);
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
        PagedSearchIterable<GHCommit> commits = getCommits(TimeUtil.getBeforeNDays(TimeUtil.DateFormat.yyyy_MM_dd, -7));
        Iterator<GHCommit> it = commits.iterator();

        List<ProjectLabelDTO> list = new ArrayList<>();
        log.info("labels ==========");
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
                log.debug("label : {]", dto.toString());
            }
            log.info("labelCount : {}", list.size());
        }
        return list;
    }

    /**
     * <pre>
     *     commit 을 기준으로 repository 이름을 가져온다.
     *     >> commit 이 없는 repository 는 조회할 수 없나..?
     *     FIXME 수정 필요.
     * </pre>
     *
     * @return
     * @throws IOException
     */
    public List<ProjectRepositoryDTO> getRepositorys() throws IOException {
        PagedSearchIterable<GHCommit> commits = getCommits(TimeUtil.getBeforeNDays(TimeUtil.DateFormat.yyyy_MM_dd, -7));
        Iterator<GHCommit> it = commits.iterator();

        List<ProjectRepositoryDTO> list = new ArrayList<>();

        log.info("== tb_project_repository ==========");
        String beforeRepoName = "";
        while (it.hasNext()) {
            GHCommit commit = it.next();

            ProjectRepositoryDTO dto = new ProjectRepositoryDTO();
            GHRepository repository = commit.getOwner();
            dto.setName(repository.getName());
            dto.setFullName(repository.getFullName());
            dto.setDescription(repository.getDescription());
            dto.setPrivacy(repository.isPrivate());
            dto.setHtmlUrl(repository.getHtmlUrl().toString());
            dto.setSshUrl(repository.getSshUrl());
            list.add(dto);
            log.debug("repository : {]", dto.toString());
            if (beforeRepoName.equals(dto.getName())) {
                break;
            } else {
                beforeRepoName = dto.getName();
            }
        }
        log.info("projectCount : {}", list.size());
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
