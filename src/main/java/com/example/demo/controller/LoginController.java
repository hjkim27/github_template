package com.example.demo.controller;

import com.example.demo.LoginMessageEnum;
import com.example.demo.bean.AjaxResponse;
import com.example.demo.bean.dto.AdminRequestDTO;
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
import java.io.UnsupportedEncodingException;

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
public class LoginController {

    private final AdminInfoService adminInfoService;

    /**
     * <pre>
     *     login page
     * </pre>
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView("/login/login");
        if (LoginUtil.isLogin(request)) {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/test"));
        } else {
            mav.addObject("message", "로그인이 필요합니다.");
        }
        return mav;
    }

    /**
     * <pre>
     *     login submit
     * </pre>
     *
     * @param dto      {@link AdminRequestDTO}
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AjaxResponse login(
            AdminRequestDTO dto
            , HttpServletRequest request
            , HttpServletResponse response
    ) {
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
                responseDTO.setUrl(request.getContextPath() + "/test");
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

}
