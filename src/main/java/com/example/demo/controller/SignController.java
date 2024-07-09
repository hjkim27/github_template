package com.example.demo.controller;

import com.example.demo.LoginMessageEnum;
import com.example.demo.bean.AjaxResponse;
import com.example.demo.bean.dto.AdminRequestDTO;
import com.example.demo.config.GeneralConfig;
import com.example.demo.service.AdminInfoService;
import com.example.demo.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *     login, logout
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 07
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class SignController {

    private final AdminInfoService adminInfoService;

    /**
     * <pre>
     *     login page
     * </pre>
     *
     * @param request {@link HttpServletRequest}
     */
    @RequestMapping("/sign/sign-in")
    public ModelAndView signIn(HttpServletRequest request) {
        log.info(GeneralConfig.START);
        ModelAndView mav = new ModelAndView("/sign/signIn");
        if (LoginUtil.isLogin(request)) {
            return new ModelAndView(new RedirectView(request.getContextPath() + GeneralConfig.MAIN_URL));
        }
        return mav;
    }

    /**
     * <pre>
     *     login submit
     * </pre>
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param dto      {@link AdminRequestDTO}
     * @return {@link AjaxResponse}
     */
    @ResponseBody
    @RequestMapping(value = "/sign/sign-in", method = RequestMethod.POST)
    public AjaxResponse signIn(
            HttpServletRequest request, HttpServletResponse response,
            AdminRequestDTO dto
    ) {
        log.info(GeneralConfig.START);
        AjaxResponse responseDTO = new AjaxResponse();

        AdminRequestDTO dto2 = new AdminRequestDTO();
        dto2.setLoginId(dto.getLoginId());

        boolean existLoginId = adminInfoService.isExistAdmin(dto2);
        boolean login = false;
        if (existLoginId) {
            int adminSid = adminInfoService.getAdminSid(dto);
            if (adminSid > 0) {
                LoginUtil.setLogin(request, response, adminSid);
                responseDTO.setStatus(0);
                responseDTO.setUrl(request.getContextPath() + GeneralConfig.MAIN_URL);
                login = true;
            } else {
                responseDTO.setErrorMessage(LoginMessageEnum.notMatch);
            }
        } else {
            responseDTO.setErrorMessage(LoginMessageEnum.notExist);
        }
        responseDTO.setLogin(login);
        return responseDTO;
    }


    /**
     * <pre>
     *     가입페이지
     * </pre>
     */
    @RequestMapping("/sign/sign-up")
    public ModelAndView signUp() {
        log.info(GeneralConfig.START);
        return new ModelAndView("sign/signUp");
    }
}
