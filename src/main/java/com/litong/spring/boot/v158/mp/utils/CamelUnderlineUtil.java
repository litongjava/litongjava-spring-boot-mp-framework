package com.litong.spring.boot.v158.mp.utils;

import com.litong.spring.boot.v158.mp.utils.string.StringUtils;

public class CamelUnderlineUtil {

  private static final char UNDERLINE = '_';

  public static String camelToUnderline(String param) {
    if (StringUtils.isEmpty(param)) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int len = param.length();
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.append(UNDERLINE);
        sb.append(Character.toLowerCase(c));
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  public static String underlineToCamel(String param) {
    if (StringUtils.isEmpty(param)) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    int len = param.length();
    for (int i = 0; i < len; i++) {
      char c = param.charAt(i);
      if (c == UNDERLINE) {
        if (++i < len) {
          sb.append(Character.toUpperCase(param.charAt(i)));
        }
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

}