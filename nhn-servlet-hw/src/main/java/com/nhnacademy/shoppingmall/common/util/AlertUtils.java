package com.nhnacademy.shoppingmall.common.util;

import javax.servlet.http.HttpServletRequest;

public class AlertUtils {

  public AlertUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static String alert(HttpServletRequest req, String msg, String url) {
    req.setAttribute("msg", msg);
    req.setAttribute("url", url);
    return "alert/alert";
  }

}
