package com.github.hjkim27.config;

import com.github.hjkim27.bean.dto.project.GhIssueDTO;
import com.github.hjkim27.bean.dto.project.GhLabelDTO;
import com.github.hjkim27.bean.dto.project.GhRepositoryDTO;
import com.github.hjkim27.service.GitService;
import com.github.hjkim27.util.DateFormatUtil;
import com.github.hjkim27.util.GitUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FixedCronSetting {

    private final GitService projectService;
    private final GitUtil gitUtil;

//    @Scheduled(cron = "*/1 * * * * *")
//    public void check() {
//        log.info(DateFormatUtil.getNowDate(DateFormatUtil.DateFormat.yyyy_MM_dd_hhmmss));
//    }

    // 매일 0시 5분에
    @Scheduled(cron = "0 5 0 */1 * *")
    public void setCommitInterval1Day() {
        gitUtil.getCommits();
        projectService.updateActiveFalse();
    }

    // 매 시간마다
    @Scheduled(cron = "0 0 */1 * * *")
    public void commits() {
        gitUtil.getCommits(DateFormatUtil.getBeforeNDays(DateFormatUtil.DateFormat.yyyy_MM_dd, -1));
    }

    /**
     * 매일 자정에 확인하도록 cron 등록
     * 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7)
     */
//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 0/5 * * * ?")
    public void insertLabelRepo() throws IOException {
        log.info(GeneralConfig.START);
        try {

            // insert owner
            // insert repository
            List<GhRepositoryDTO> repoList = gitUtil.getRepositories();
            projectService.insertRepos(repoList);

            // insert issue
            // insert comment, event
            List<GhIssueDTO> issueList = gitUtil.getIssues();
            projectService.insertIssues(issueList);

            // insert label
            List<GhLabelDTO> labelList = gitUtil.getLabels();
            projectService.insertLabels(labelList);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
