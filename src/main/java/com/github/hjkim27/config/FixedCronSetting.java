package com.github.hjkim27.config;

import com.github.hjkim27.bean.dto.project.ProjectCommitDTO;
import com.github.hjkim27.bean.dto.project.ProjectLabelDTO;
import com.github.hjkim27.bean.dto.project.ProjectRepositoryDTO;
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

    @Scheduled(cron = "*/1 * * * * *")
    public void check() {
        log.info(DateFormatUtil.getNowDate(DateFormatUtil.DateFormat.yyyy_MM_dd_hhmmss));
    }

    @Scheduled(cron = "* * */1 * * *")
    public void commits() {
        gitUtil.getCommits(DateFormatUtil.getBeforeNDays(DateFormatUtil.DateFormat.yyyy_MM_dd, -1));
    }

    /**
     * 매일 자정에 확인하도록 cron 등록
     * 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7)
     */
//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 */2 * * * *")
    public void insertLabels() throws IOException {
        // insert label
        try {
            List<ProjectLabelDTO> labelList = gitUtil.getLabels();
            projectService.insertLabels(labelList);

            // insert repository
            List<ProjectRepositoryDTO> repoList = gitUtil.getRepositorys();
            projectService.insertRepos(repoList);

            List<ProjectCommitDTO> commitList = gitUtil.getCommit();
//            projectService.insertCommit(commitList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
