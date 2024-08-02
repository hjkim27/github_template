package com.github.hjkim27.controller;

import com.github.hjkim27.config.GeneralConfig;
import com.github.hjkim27.util.login.LoginUtil;
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
    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request) {
        log.info(GeneralConfig.START);
        ModelAndView mav = new ModelAndView(new RedirectView(request.getContextPath() + GeneralConfig.REPO_URL));
        if (!LoginUtil.isLogin(request)) {
            mav = new ModelAndView(new RedirectView(request.getContextPath() + GeneralConfig.SIGN_IN_URL));
        }
        return mav;
    }

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request) {
        return new ModelAndView(new RedirectView(request.getContextPath() + GeneralConfig.HOME_URL));
    }
}
