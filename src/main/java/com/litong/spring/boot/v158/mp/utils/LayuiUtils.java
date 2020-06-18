package com.litong.spring.boot.v158.mp.utils;

import com.litong.spring.boot.v158.mp.vo.JsonBean;

/**
 * @author bill robot
 * @date 2020年6月18日_下午5:07:59 
 * @version 1.0 
 * @desc
 */

public class LayuiUtils {

  public static JsonBean<Boolean> buildJsonBean(String methodName, boolean b) {
    JsonBean<Boolean> jsonBean = new JsonBean<>();
    if (b) {
      return jsonBean;
    } else {
      jsonBean.setCode(-1);
      jsonBean.setMsg(methodName + " fail");
    }
    return jsonBean;
  }
}
