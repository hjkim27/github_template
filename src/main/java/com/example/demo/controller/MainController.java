package com.example.demo.controller;

import com.example.demo.config.GeneralConfig;
import com.example.demo.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    /**
     * <pre>
     *     메인페이지
     * </pre>
     *
     * @param request {@link HttpServletRequest}
     * @return {@link ModelAndView}
     */
    @RequestMapping("/main")
    public ModelAndView boardMain(HttpServletRequest request) {
        log.info(GeneralConfig.START);
        ModelAndView mav = new ModelAndView("/board/main");
        if (!LoginUtil.isLogin(request)) {
            mav.setViewName("/main");
        }
        return mav;
    }

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request) {
        return new ModelAndView(new RedirectView(request.getContextPath() + GeneralConfig.MAIN_URL));
    }
}
