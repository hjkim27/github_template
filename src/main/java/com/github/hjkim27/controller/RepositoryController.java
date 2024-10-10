package com.github.hjkim27.controller;

import com.github.hjkim27.bean.dto.project.GhLabelDTO;
import com.github.hjkim27.bean.search.ProjectSearch;
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
public class RepositoryController {

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
    @RequestMapping(value = "/projectRepository/{path}")
    public ModelAndView projectSelectMenu(HttpServletRequest request, HttpServletResponse response
            , @PathVariable(required = false) String path
            , @ModelAttribute(name = "search") ProjectSearch search) throws Exception {
        log.info(GeneralConfig.START);
        log.debug("search : {}", search);
        ModelAndView mav = new ModelAndView("projectRepository/main");

        switch (path) {
            case "home":
                mav.addAllObjects(home());
                break;
            case "repositories":
                search.setSortColumn(1);
                mav.addAllObjects(repositories(search));
                break;
            case "issues":
                search.setDesc(true);
                search.setSortColumn(2);
                mav.addAllObjects(issues(search));
                break;
            case "labels":
                search.setSortColumn(4);
                mav.addAllObjects(labels(search));
                break;
            case "settings":
                mav.addAllObjects(settings());
                break;
            default:
                throw new Exception("does not exist path!!!");
        }

        mav.addObject("path", path);
        mav.addObject("search", search);
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
    @RequestMapping(value = "/projectRepository/ajax/{path}")
    public ModelAndView projectAjax(HttpServletRequest request, HttpServletResponse response
            , @PathVariable(required = false) String path
            , @ModelAttribute(name = "search") ProjectSearch search) throws Exception {
        log.info(GeneralConfig.START);
        log.debug("search : {}", search);
        ModelAndView mav = new ModelAndView("projectRepository/ajax/" + path);

        switch (path) {
            case "home":
                mav.addAllObjects(home());
                break;
            case "repositories":
                mav.addAllObjects(repositories(search));
                break;
            case "issues":
                mav.addAllObjects(issues(search));
                break;
            case "labels":
                mav.addAllObjects(labels(search));
                break;
            case "settings":
                mav.addAllObjects(settings());
                break;
            default:
                throw new Exception("does not exist path!!!");
        }

        mav.addObject("path", path);
        mav.addObject("search", search);
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
    public Map<String, Object> repositories(ProjectSearch search) {
        Map<String, Object> map = new HashMap<>();
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
    public Map<String, Object> issues(ProjectSearch search) {

        // [2024-10-11] state 검색관련 추가
        // issue state, pull_request 여부를 추가 확인하기 위해 searchValue 가공
        String value = search.getSearchValue();
        if (value != null) {
            String[] arr = value.split(" ");
            String valueNew = "";
            List<String> list = new ArrayList<>();
            for (String s : arr) {
                if (s.contains("is:")) {
                    list.add(s);
                } else {
                    valueNew = s;
                }
            }
            search.setSearchValueList(list);
            search.setSearchValue(valueNew);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("list", projectService.getIssueList(search));

        // sortColumn 값을 issue, label 조회 시 동일하게 사용하면서 에러 발생.
        // 별도 search 객체를 사용하도록 수정
        List<GhLabelDTO> labelList = projectService.getLabelList(new ProjectSearch(search.getRepositorySid()));
        map.put("labelList", labelList);
        map.put("labels", projectService.getLabelMap(labelList));

        // [2024-10-09] issue 상태에 따른 count 출력
        map.put("issueCount", projectService.issueStateCount(search.getRepositorySid()));

        map.put("multiType", true); // 다중검색 기능을 사용하고자 할 경우 추가
        return map;
    }

    public Map<String, Object> labels(ProjectSearch search) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", projectService.getLabelList(search));
        return map;
    }

    public Map<String, Object> settings() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }


}
