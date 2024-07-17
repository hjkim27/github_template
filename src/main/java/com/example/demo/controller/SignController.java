package com.example.demo.controller;

import com.example.demo.bean.SignMessageEnum;
import com.example.demo.bean.dto.AdminRequestDTO;
import com.example.demo.bean.dto.AjaxResponseDTO;
import com.example.demo.config.GeneralConfig;
import com.example.demo.service.AdminInfoService;
import com.example.demo.util.LoginUtil;
import com.hjkim27.util.enc.SHAUtils;
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
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *     login, logout
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
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
     * @param dto      loginId, loginPw 를 담은 {@link AdminRequestDTO} 객체
     * @return {@link AjaxResponseDTO}
     */
    @ResponseBody
    @RequestMapping(value = "/sign/sign-in", method = RequestMethod.POST)
    public AjaxResponseDTO signIn(
            HttpServletRequest request, HttpServletResponse response,
            AdminRequestDTO dto
    ) throws NoSuchAlgorithmException {
        log.info(GeneralConfig.START);
        log.debug("parameter : {}", dto);
        AjaxResponseDTO responseDTO = new AjaxResponseDTO();
        SignMessageEnum signMessageEnum = null;

        AdminRequestDTO adminRequestIDCheck = new AdminRequestDTO();
        adminRequestIDCheck.setLoginId(dto.getLoginId());

        int adminSid = adminInfoService.getAdminSid(adminRequestIDCheck);
        if (adminSid > 0) {
            // 비밀번호 암호화
            String passwd = dto.getLoginPw();
            String encPasswd = SHAUtils.get256EncryptWithSalt(passwd);
            dto.setLoginPw(encPasswd);

            adminSid = adminInfoService.getAdminSid(dto);
            if (adminSid > 0) {
                LoginUtil.setLogin(request, response, adminSid);
                responseDTO.setUrl(request.getContextPath() + GeneralConfig.MAIN_URL);
                signMessageEnum = SignMessageEnum.SUCCESS;
            } else {
                signMessageEnum = SignMessageEnum.NOT_MATCH_PASSWORD;
            }
        } else {
            signMessageEnum = SignMessageEnum.NOT_EXIST_USER;
        }
        responseDTO.setSignMessage(signMessageEnum);
        log.debug("return : {}", responseDTO);

        return responseDTO;
    }


    /**
     * <pre>
     *     register page
     * </pre>
     */
    @RequestMapping("/sign/sign-up")
    public ModelAndView signUp() {
        log.info(GeneralConfig.START);
        return new ModelAndView("sign/signUp");
    }

    /**
     * <pre>
     *     register submit
     * </pre>
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param dto      loginId, name, loginPw, loginPwCheck 를 담은 {@link AdminRequestDTO} 객체
     * @return {@link AjaxResponseDTO}
     */
    @ResponseBody
    @RequestMapping(value = "/sign/sign-up", method = RequestMethod.POST)
    public AjaxResponseDTO signUp(
            HttpServletRequest request, HttpServletResponse response,
            AdminRequestDTO dto
    ) throws NoSuchAlgorithmException {
        log.info(GeneralConfig.START);
        log.debug("parameter : {}", dto);

        AjaxResponseDTO responseDTO = new AjaxResponseDTO();
        SignMessageEnum signMessageEnum = null;


        // 비밀번호 일치
        if (dto.getLoginPw().equals(dto.getLoginPwCheck())) {
            AdminRequestDTO adminRequestIDCheck = new AdminRequestDTO();
            adminRequestIDCheck.setLoginId(dto.getLoginId());

            int adminSid = adminInfoService.getAdminSid(adminRequestIDCheck);
            // 아이디 사용 불가
            if (adminSid > 0) {
                signMessageEnum = SignMessageEnum.UNABLE_USER_ID;
            }
            // 아이디 사용 가능
            else {
                // 비밀번호 암호화
                String passwd = dto.getLoginPw();
                String encPasswd = SHAUtils.get256EncryptWithSalt(passwd);
                dto.setLoginPw(encPasswd);

                int insertLoginId = adminInfoService.insertAdmin(dto);
                // 계정 추가 성공
                if (insertLoginId > 0) {
                    LoginUtil.setLogin(request, response, insertLoginId);
                    signMessageEnum = SignMessageEnum.SUCCESS;
                }
                // 계정 추가 실패
                else {
                    signMessageEnum = SignMessageEnum.FAILED_REGISTER_USER;
                }
            }
        }
        // 비밀번호 불일치
        else {
            signMessageEnum = SignMessageEnum.NOT_MATCH_PASSWORD;
        }
        responseDTO.setSignMessage(signMessageEnum);
        log.debug("return : {}", responseDTO);

        return responseDTO;
    }

}
