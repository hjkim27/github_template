package com.github.hjkim27.controller;

import com.github.hjkim27.config.GeneralConfig;
import com.github.hjkim27.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
            , @PathVariable(required = false) String path) throws Exception {
        log.info(GeneralConfig.START);
        ModelAndView mav = new ModelAndView("projectRepository/main");

        switch (path) {
            case "home":
                mav.addAllObjects(home());
                break;
            case "repositories":
                mav.addAllObjects(repositories());
                break;
            case "labels":
                mav.addAllObjects(labels());
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

    public Map<String, Object> repositories() {
        Map<String, Object> map = new HashMap<>();
        map.put("list", projectService.getRepoList());
        return map;
    }

    public Map<String, Object> labels() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    public Map<String, Object> settings() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }


}
