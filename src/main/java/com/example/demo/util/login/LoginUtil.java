package com.example.demo.util.login;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *     로그인, 로그아웃, 로그인 확인을 위한 유틸리티 클래스
 * </pre>
 *
 * @author hjkim27
 * @since 0.0.1-SNAPSHOT
 */
@Slf4j
public class LoginUtil {

    // sid가 저장되는 cookie 이름
    public static final String COOKIE_NAME = "hjkim27LoginId";

    /**
     * <pre>
     *     사용자의 권한 확인
     * </pre>
     *
     * @param request
     * @return
     */
    public static int getAdminId(HttpServletRequest request) {
        int id = -1;
        try {
            String adminSidStr = CookieUtil.getCookie(request, COOKIE_NAME);
            if (adminSidStr != null) {
                id = Integer.parseInt(adminSidStr);
            }
        } catch (NumberFormatException nfe) {
            log.error("로그인 정보를 가져 오는 도중에 오류가 발생하였습니다.");
        } catch (Exception e) {
            log.error("로그인 정보를 가져 오는 도중에 오류가 발생하였습니다.\n{} | {}", e, e.getMessage());
        }

        return id;
    }


    /**
     * <pre>
     *     사용자 로그인여부 확인
     * </pre>
     *
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request) {
        boolean isLogin = false;
        try {
            String adminSidStr = CookieUtil.getCookie(request, COOKIE_NAME);
            if (adminSidStr != null) {
                isLogin = true;
            }
        } catch (Exception e) {
            log.error("{} | {}", e.getMessage(), e);
            log.error("로그인 정보를 확인 하는 도중에 오류가 발생하였습니다.");
        }
        return isLogin;
    }

    /**
     * <pre>
     *     로그인: cookie 로 저장
     * </pre>
     *
     * @param request
     * @param response
     * @param sid      사용자 sid
     */
    public static void setLogin(HttpServletRequest request, HttpServletResponse response, int sid) {
        try {
            CookieUtil.setCookie(response, COOKIE_NAME, Integer.toString(sid));
        } catch (Exception e) {
            log.error("로그인 정보를 입력 하는 도중 오류가 발생하였습니다.\n{} | {}", e, e.getMessage());
        }
    }

    /**
     * <pre>
     *     로그아웃
     * </pre>
     *
     * @param request
     * @param response
     */
    public static void setLogout(HttpServletRequest request, HttpServletResponse response) {
        int sid = getAdminId(request);
        if (sid == -1) {
            return;
        }
        CookieUtil.removeCookie(response, COOKIE_NAME);

    }

}
