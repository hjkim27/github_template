package com.example.demo.util;

import com.example.demo.config.GeneralConfig;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class GitUtil {

    private static GitHub gitHub;
    private static final String token = "ghp_authToken";
    private static final String userId = "hjkim27.dev@gmail.com";

    private static Map<String, Integer> commitCounts = new LinkedHashMap<>();

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static void connectToGithub(String token) throws IOException {
        gitHub = new GitHubBuilder().withAppInstallationToken(token).build();
        gitHub.checkApiUrlValidity();
    }

    public static PagedIterator<GHCommit> getCommits() throws IOException {
        log.info(GeneralConfig.START);
        try {
            connectToGithub(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        GHSearchBuilder builder = gitHub.searchCommits()
//                .author(userId)
                .authorEmail(userId)
                .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE);

        PagedSearchIterable<GHCommit> commits = builder.list().withPageSize(10);

        Iterator<GHCommit> it = commits.iterator();
        int i = 0;
        while (it.hasNext()) {
            GHCommit commit = it.next();
            log.info("{} {}", commit.getCommitShortInfo().getCommitDate(), commit.getCommitShortInfo().getMessage());
        }

        return commits._iterator(1);
    }

    public static void getCommits2() throws IOException {
        log.info(GeneralConfig.START);
        try {
            connectToGithub(token);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        GHSearchBuilder builder = gitHub.searchCommits()
                .authorEmail(userId)
                .sort(GHCommitSearchBuilder.Sort.AUTHOR_DATE);

        PagedSearchIterable<GHCommit> commits = builder.list();

        Iterator<GHCommit> it = commits.iterator();
        int i = 0;
        while (it.hasNext()) {
            GHCommit commit = it.next();
            i++;
            String key = sdf.format(commit.getCommitShortInfo().getCommitDate());
            if(commitCounts.containsKey(key)){
                Integer value = commitCounts.get(key);
                commitCounts.put(key, ++value);
            } else {
                commitCounts.put(key, 1);
            }
            log.info("{} {}", key, commit.getCommitShortInfo().getMessage().replaceAll("\r\n", " > "));
        }
        log.info("total {}", i);
        log.info("-----------");
        for(String key: commitCounts.keySet()){
            log.info("{} > {}", key, commitCounts.get(key));
        }
        log.info("-----------");

    }

    public static void main(String[] args) throws IOException {
        GitUtil.getCommits2();
    }
}
