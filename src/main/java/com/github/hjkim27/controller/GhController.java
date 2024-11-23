package com.github.hjkim27.controller;

import com.github.hjkim27.bean.dto.project.GhLabelDTO;
import com.github.hjkim27.bean.search.GhSearch;
import com.github.hjkim27.config.GeneralConfig;
import com.github.hjkim27.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GhController {

    private final ProjectService projectService;

    /**
     * <pre>
     *     메인헤더에서 클릭한 메뉴페이지 url
     * </pre>
     *
     * @param request
     * @param response
     * @param path
     * @return
     */
    @RequestMapping(value = "/ghRepository/{path}")
    public ModelAndView projectSelectMenu(HttpServletRequest request, HttpServletResponse response
            , @PathVariable(required = false) String path
            , @ModelAttribute(name = "search") GhSearch search) throws Exception {
        log.info(GeneralConfig.START);
        log.debug("search : {}", search);
        ModelAndView mav = new ModelAndView("ghRepository/" + path + "/main");
        if (path.equals("pulls")) {
            mav = new ModelAndView("ghRepository/issues/main");
        }
        mav.addAllObjects(projectAjax(request, response, path, search).getModel());
        return mav;
    }

    /**
     * <pre>
     *     각 페이지에서 반환될 ajax 결과 페이지
     * </pre>
     *
     * @param request
     * @param response
     * @param path
     * @param search
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ghRepository/ajax/{path}")
    public ModelAndView projectAjax(HttpServletRequest request, HttpServletResponse response
            , @PathVariable(required = false) String path
            , @ModelAttribute(name = "search") GhSearch search) throws Exception {
        log.info(GeneralConfig.START);
        log.debug("search : {}", search);
        ModelAndView mav = new ModelAndView("ghRepository/" + path + "/list");

        switch (path) {
            case "home":
                mav.addAllObjects(home());
                break;
            case "repositories":
                mav.addAllObjects(repositories(search));
                break;
            case "issues":
            case "pulls":
                mav = new ModelAndView("ghRepository/issues/list");
                mav.addAllObjects(issues(search));
                break;
            case "labels":
                mav.addAllObjects(labels(search));
                break;
            case "commits":
                mav.addAllObjects(commits(search));
                break;
            case "settings":
                mav.addAllObjects(settings());
                break;
            default:
                throw new Exception("does not exist path!!!");
        }

        mav.addObject("path", path);
        return mav;
    }

    public Map<String, Object> home() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    /**
     * <pre>
     *     repositories 검색 결과
     * </pre>
     *
     * @param search
     * @return
     */
    public Map<String, Object> repositories(GhSearch search) {

        // 정렬 기본값 추가
        if (search.getSortColumn() == -1) {
            search.setSortColumn(3);
            search.setDesc(true);
        }

        Map<String, Object> map = new HashMap<>();
        search.setTotalSize(projectService.getRepoTotalCount(search));
        map.put("search", search);
        map.put("list", projectService.getRepoList(search));
        map.put("filterType", true);    // [2024-09-19] filterType 검색 사용여부 추가
        return map;
    }

    /**
     * <pre>
     *     issues 검색 결과
     * </pre>
     *
     * @param search
     * @return
     */
    public Map<String, Object> issues(GhSearch search) {

        // 정렬 기본값 추가
        if (search.getSortColumn() == -1) {
            search.setSortColumn(2);
            search.setDesc(true);
        }

        // [2024-10-11] state 검색관련 추가
        // issue state, pull_request 여부를 추가 확인하기 위해 searchValue 가공
        String value = search.getSearchValue();
        if (value != null) {
            String[] arr = value.split(" ");
            String valueNew = "";
            List<String> list = new ArrayList<>();
            for (String s : arr) {
                if (s.contains("is:")) {
                    if (s.contains("pulls")) {
                        search.setSearchType(1);
                    } else if (s.contains("issues")) {
                        search.setSearchType(0);
                    } else {
                        list.add(s);
                    }
                } else {
                    valueNew = s;
                }
            }
            search.setSearchValueList(list);
            search.setSearchValue(valueNew);
        }

        Map<String, Object> map = new HashMap<>();
        search.setTotalSize(projectService.getIssueTotalCount(search));
        map.put("list", projectService.getIssueList(search));

        // sortColumn 값을 issue, label 조회 시 동일하게 사용하면서 에러 발생.
        // 별도 search 객체를 사용하도록 수정
        List<GhLabelDTO> labelList = projectService.getLabelList(new GhSearch(search.getRepositorySid()));
        map.put("labelList", labelList);
        map.put("labels", projectService.getLabelMap(labelList));

        // [2024-10-09] issue 상태에 따른 count 출력
        map.put("issueCount", projectService.issueStateCount(search.getRepositorySid()));

        map.put("multiType", true); // 다중검색 기능을 사용하고자 할 경우 추가

        search.setSearchValue(value);
        map.put("search", search);
        return map;
    }

    /**
     * <pre>
     *     label 검색 결과
     * </pre>
     *
     * @param search
     * @return
     */
    public Map<String, Object> labels(GhSearch search) {

        // 정렬 기본값 추가
        if (search.getSortColumn() == -1) {
            search.setSortColumn(1);
        }

        Map<String, Object> map = new HashMap<>();
        search.setTotalSize(projectService.getLabelTotalCount(search));
        map.put("search", search);
        map.put("list", projectService.getLabelList(search));
        return map;
    }

    /**
     * <pre>
     *     commit 목록 조회
     *     [2024-11-01] 같은날 commit 한 내용끼리 묶기 위해 list 형식 수정
     * </pre>
     *
     * @param search
     * @return
     */
    public Map<String, Object> commits(GhSearch search) {
        Map<String, Object> map = new HashMap<>();
        search.setTotalSize(projectService.getCommitTotalCount(search));
        map.put("search", search);
        map.put("list", projectService.getCommitsGroupDate(search));
        return map;
    }

    public Map<String, Object> settings() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }


}
