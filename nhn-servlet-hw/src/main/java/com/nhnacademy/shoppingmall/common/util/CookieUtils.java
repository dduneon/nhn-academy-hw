package com.nhnacademy.shoppingmall.common.util;

import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

  public CookieUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static Cookie getCookie(HttpServletRequest req, String cookieId) {
    return Optional.ofNullable(req.getCookies())
        .flatMap(cookies -> Arrays.stream(cookies)
            .filter(c -> c.getName().equals(cookieId))
            .findFirst())
        .orElse(null);
  }
}
