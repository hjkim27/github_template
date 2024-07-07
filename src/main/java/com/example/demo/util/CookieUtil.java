package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <pre>
 *      쿠키 정보를 가져오는 유틸리티 클래스
 * </pre>
 *
 * @author hjkim27
 * @date 2024. 07. 07
 */
@Slf4j
public class CookieUtil {

    private static String ENCODE_CHARSET = "UTF-8";

    /**
     * @param request
     * @param cookieName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getCookie(HttpServletRequest request, String cookieName) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                String cookieValue = cookie.getValue();
                return (cookieValue == null) ? cookieValue : URLDecoder.decode(cookieValue, ENCODE_CHARSET);
            }
        }
        return null;
    }

    /**
     * @param response
     * @param cookieName
     * @param cookieValue
     * @throws UnsupportedEncodingException
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue) throws UnsupportedEncodingException {
        setCookie(response, cookieName, cookieValue, 60 * 60 + 3);
    }

    /**
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param age
     * @throws UnsupportedEncodingException
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int age) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, ENCODE_CHARSET));
        cookie.setPath("/");
        cookie.setMaxAge(age);
        cookie.setVersion(0);
        cookie.setSecure(false);

        /**
         * https://www.w3.org/TR/P3P/
         */
        response.setHeader("P3P", "CP='CAO DSP COR CURa ADMa DEVa OUR IND NAV'");
        response.addCookie(cookie);
    }

    /**
     * @param response
     * @param cookieName
     */
    public static void removeCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setVersion(0);
        cookie.setSecure(false);
        response.addCookie(cookie);
    }

}
