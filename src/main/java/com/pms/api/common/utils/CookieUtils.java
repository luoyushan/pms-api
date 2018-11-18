package com.pms.api.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {
  private static final int MAX_AGE = 10 * 60;
  private static final String PATH = "/";

  /**
   * 获取cookie
   * @param request 请求
   * @param cookieName 名
   */
  public static String getCookie(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  /**
   * 保存cookie
   * @param response 响应
   * @param cookieName 名
   * @param value 值
   */

  public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
    Cookie cookie = new Cookie(cookieName, value);
    cookie.setPath(PATH);
    cookie.setMaxAge(MAX_AGE);
    response.addCookie(cookie);
  }

}
