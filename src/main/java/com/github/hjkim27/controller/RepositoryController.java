package com.github.hjkim27.controller;

import com.github.hjkim27.config.GeneralConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class RepositoryController {

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
            , @PathVariable(required = false) String path) {
        log.info(GeneralConfig.START);
        ModelAndView mav = new ModelAndView("projectRepository/" + path);
        mav.addObject("path", path);

        return mav;
    }
}
