package com.github.hjkim27.controller;

import com.github.hjkim27.bean.search.GhDetailSearch;
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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GhDetailController {

    private final ProjectService projectService;

    @ResponseBody
    @RequestMapping(value = "/ghRepository/detail/{path}")
    public ModelAndView detail(HttpServletRequest request, HttpServletRequest response
            , @PathVariable String path
            , @ModelAttribute(name = "search") GhDetailSearch search) throws Exception {
        log.info(GeneralConfig.START);
        log.debug("search : {}", search);
        ModelAndView mav = new ModelAndView("ghRepository/" + path + "/detail");

        switch (path) {
            case "issues":
                mav.addAllObjects(issues(search));
                break;
            case "commits":
                mav.addAllObjects(commits(search));
                break;
            default:
                throw new Exception("does not exist path!!");
        }
        mav.addObject("path", path);
        mav.addObject("search", search);
        return mav;
    }

    public Map<String, Object> issues(GhDetailSearch search) {
        Map<String, Object> map = new HashMap<>();
        map.put("item", projectService.getIssue(search));
        return map;
    }

    public Map<String, Object> commits(GhDetailSearch search) {
        Map<String, Object> map = new HashMap<>();
        map.put("item", projectService.getCommit(search));
        return map;
    }
}
