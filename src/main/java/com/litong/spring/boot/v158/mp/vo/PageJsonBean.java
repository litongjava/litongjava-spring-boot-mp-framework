package com.litong.spring.boot.v158.mp.vo;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

public class PageJsonBean<T> extends JsonBean<List<T>> {

  private long count;

  public long getCount() {
    return count;
  }

  public PageJsonBean(IPage<T> page) {
    super(page.getRecords());
    this.count = page.getTotal();
  }

}
