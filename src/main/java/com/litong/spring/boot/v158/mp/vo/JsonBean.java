package com.litong.spring.boot.v158.mp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonBean<T> {

  private int code = 0; // 状态码

  private String msg = "执行成功"; // 状态说明

  private T data; // 数据内容

  public JsonBean(T data) {
    super();
    this.data = data;
  }

  public JsonBean(int code) {
    super();
    this.code = code;
  }

  public JsonBean(String msg) {
    this.msg=msg;
  }

  public JsonBean(int code, String msg) {
    super();
    this.code = code;
    this.msg = msg;
  }
}
