package com.example.demo.config;

import com.example.demo.bean.dto.project.ProjectIssueDTO;
import com.example.demo.bean.dto.project.ProjectLabelDTO;
import com.example.demo.bean.dto.project.ProjectRepositoryDTO;
import com.example.demo.service.ProjectService;
import com.example.demo.util.GitUtil;
import com.example.demo.util.DateFormatUtil;
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

    private final ProjectService projectService;
    private final GitUtil gitUtil;

    @Scheduled(cron = "*/1 * * * * *")
    public void check() {
        log.info(DateFormatUtil.getNowDate(DateFormatUtil.DateFormat.yyyy_MM_dd_hhmmss));
    }

    /**
     * 매일 자정에 확인하도록 cron 등록
     * 초(0-59)   분(0-59)　　시간(0-23)　　일(1-31)　　월(1-12)　　요일(0-7)
     */
//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0 */1 * * * *")
    public void insertLabels() throws IOException {
        // insert label
        try {
            List<ProjectLabelDTO> labelList = gitUtil.getLabels();
            projectService.insertLabels(labelList);

            // insert repository
            List<ProjectRepositoryDTO> repoList = gitUtil.getRepositorys();
            projectService.insertRepos(repoList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
